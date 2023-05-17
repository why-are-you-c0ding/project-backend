package wayc.backend.pay.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.repository.PayRepository;

import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void createPay(Long memberId, CreatePayRequestDto dto) {
        Order order = orderRepository.findOrderByOrderIdAndOrderingMemberIdAndOrderStatus(dto.getOrderId(), memberId)
                            .orElseThrow(NotExistsOrderException::new);

        //TODO: 외부 결제사 연동.

        Pay pay = new Pay(dto.getPay(), dto.getOrderId());
        payRepository.save(pay);
        order.updateOrder(OrderStatus.ONGOING);
    }

    @Transactional(readOnly = false)
    public void createPays(Long memberId, List<CreatePayRequestDto> dtoList ) {
        List<Pay> result = dtoList
                .stream()
                .map(dto -> {
                    Order order = orderRepository
                            .findOrderByOrderIdAndOrderingMemberIdAndOrderStatus(
                                    dto.getOrderId(),
                                    memberId
                            )
                            .orElseThrow(NotExistsOrderException::new);
                    order.updateOrder(OrderStatus.ONGOING);
                    return new Pay(dto.getPay(), dto.getOrderId());
                })
                .collect(Collectors.toList());
        payRepository.saveAll(result);
    }
}


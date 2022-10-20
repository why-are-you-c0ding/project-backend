package wayc.backend.pay.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.exception.order.NotExistsOrderException;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.infrastructure.OrderRepository;

import wayc.backend.pay.application.dto.request.CreatePayRequestDto;
import wayc.backend.pay.application.dto.response.CreatePayResponseDto;
import wayc.backend.pay.domain.Pay;
import wayc.backend.pay.infrastructure.PayRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = false)
    public void createPay(Long memberId, List<CreatePayRequestDto> dtoList ) {

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
                    return new Pay(dto.getPrice(), dto.getOrderId());
                })
                .collect(Collectors.toList());
        payRepository.saveAll(result);
    }
}

//    @Transactional(readOnly = false)
//    public void createPay(Long memberId, Long orderId, Integer price) {
//        Order order = orderRepository.findOrderByOrderIdAndOrderingMemberIdAndOrderStatus(orderId, memberId)
//                .orElseThrow(NotExistsOrderException::new);//검증
//        Pay pay = new Pay(price, orderId);
//        payRepository.save(pay);
//        order.updateOrder(OrderStatus.ONGOING);
//    }


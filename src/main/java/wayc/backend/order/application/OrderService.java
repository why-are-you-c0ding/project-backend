package wayc.backend.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.exception.shop.NotExistsItemException;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.response.ShowOrdersResponseDto;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.infrastructure.OrderRepository;
import wayc.backend.order.domain.Order;
import wayc.backend.shop.infrastructure.ItemRepository;
import wayc.backend.shop.domain.Item;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Transactional(readOnly = false)
    public void createOrder(List<CreateOrderRequestDto> dto, Long memberId) {
        List<Order> order = orderMapper.mapFrom(dto, memberId);
        //TODO  order vadliation을 해야함.
        orderRepository.saveAll(order);
    }

    public ShowTotalOrderResponseDto showCustomerOrders(Long memberId, Integer page) {
        PageRequest paging = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Slice<Order> pagingResult = orderRepository.findOrdersByOrderingMemberId(memberId, paging);
        List<ShowOrdersResponseDto> result = pagingResult.stream()
                .map(order -> {
                    Item item = itemRepository
                            .findByIdAndStatus(order.getItemId())
                            .orElseThrow(NotExistsItemException::new);
                    return ShowOrdersResponseDto.of(order, item);
                })
                .collect(Collectors.toList());
        ;
        return new ShowTotalOrderResponseDto(!pagingResult.hasNext(), result);
    }
}

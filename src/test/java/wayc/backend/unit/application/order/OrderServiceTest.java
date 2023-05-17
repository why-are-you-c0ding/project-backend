package wayc.backend.unit.application.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import wayc.backend.common.event.Events;
import wayc.backend.factory.order.OrderFactory;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.unit.application.UnitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

public class OrderServiceTest extends UnitTest {

    private OrderService orderService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private OrderRepository orderRepository;

    private MockedStatic<Events> mockEvents;



    @BeforeEach
    void beforeEach(){
        this.orderService = new OrderService(itemRepository, orderRepository, new OrderMapper());
        mockEvents = mockStatic(Events.class);
    }

    @AfterEach
    void afterEach(){
        mockEvents.close();
    }

    @Test
    void createOrder(){

        //given
        List<CreateOrderRequestDto> dtoList = OrderFactory.createServiceDto();

        //when
        orderService.createOrder(1L,dtoList);

        //then
        verify(orderRepository, Mockito.times(1))
                .saveAll(Mockito.any(List.class));
        mockEvents.verify(() -> Events.raise(Mockito.any()), times(1));
    }

    @Test
    void updateOrder(){

        //given
        Order order = OrderFactory.create(3L);
        given(itemRepository.findItemByShopOwnerIdAndItemId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(Optional.of(Item.builder().optionGroups(new ArrayList<>()).build()));
        given(orderRepository.findOrderByOrderIdAndItemId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(Optional.of(order));

        //when
        orderService.updateOrder(1L, new UpdateOrderRequestDto(2L, 3L, OrderStatus.COMPLETED));

        //then
        Assertions.assertThat(order.getOrderStatus()).isSameAs(OrderStatus.COMPLETED);
    }
}

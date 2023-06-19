package wayc.backend.unit.application.order;

import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import wayc.backend.common.event.Events;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.OrderFactory;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.validator.OrderValidator;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.port.ItemComparisonValidator;
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
        this.orderService = new OrderService(itemRepository, orderRepository, new OrderMapper(), new ItemComparisonValidator<>(itemRepository));
        mockEvents = mockStatic(Events.class);
    }

    @AfterEach
    void afterEach(){
        mockEvents.close();
    }

    @Test
    @DisplayName("주문을 성공적으로 생성한다.")
    void createOrder(){

        //given
        Item item = ItemFactory.createMacBook();
        List<CreateOrderRequestDto> dtoList = OrderFactory.createSuccessCaseMackBookDto();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        //when
        orderService.createOrder(1L,dtoList);

        //then
        verify(orderRepository, Mockito.times(1)).saveAll(Mockito.any(List.class));
        mockEvents.verify(() -> Events.raise(Mockito.any()), times(2));
    }

    @Test
    @DisplayName("주문 옵션이 아이템 옵션과 달라 주문 생성을 실패한다.")
    void failCreateOrderCase1(){

        //given
        Item item = ItemFactory.createMacBook();
        List<CreateOrderRequestDto> dtoList = OrderFactory.createFailCase1CaseMackBookDto();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(1L, dtoList);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 상품 이름이 상품 이름과 달라 주문 생성을 실패한다.")
    void failCreateOrderCase2(){

        //given
        Item item = ItemFactory.createMacBook();
        List<CreateOrderRequestDto> dtoList = OrderFactory.createFailCase2CaseMackBookDto();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(1L, dtoList);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
        result.hasMessage("상품 이름이 변경되었습니다.");
    }

    @Test
    @DisplayName("주문한 상품 이름의 옵션이 상품 옵션에 존재하지 않아 주문 생성을 실패한다.")
    void failCreateOrderCase3(){

        //given
        Item item = ItemFactory.createMacBook();
        List<CreateOrderRequestDto> dtoList = OrderFactory.createFailCase3CaseMackBookDto();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(1L, dtoList);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
        result.hasMessage("상품 옵션이 변경됐습니다.");
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

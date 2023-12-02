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

import org.mockito.Spy;
import wayc.backend.common.event.Events;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.CreateAddressRequestFactory;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.factory.order.OrderLineItemFactory;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderLineItemStatus;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.validator.OrderValidator;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.valid.ItemComparisonValidator;
import wayc.backend.unit.application.UnitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

public class OrderServiceTest extends UnitTest {

    private OrderService orderService;

    @Spy
    private ItemRepository itemRepository;

    @Mock
    private OrderLineItemRepository orderLineItemRepository;

    @Mock
    private OrderRepository orderRepository;

    private MockedStatic<Events> mockEvents;


    @BeforeEach
    void beforeEach(){
        this.orderService = new OrderService(
                itemRepository,
                orderRepository,
                orderLineItemRepository,
                new OrderMapper(),
                new OrderValidator(new ItemComparisonValidator<>(itemRepository))
        );
        //mockEvents = mockStatic(Events.class);
    }

//    @AfterEach
//    void afterEach(){
//        mockEvents.close();
//    }

    @Test
    @DisplayName("주문을 성공적으로 생성한다.")
    void createOrder(){

        //given
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        orderService.createOrder(CreateOrderRequestFactory.createOrderRequest(List.of(item)).toServiceDto(1L));

        //then
        verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
        //mockEvents.verify(() -> Events.raise(Mockito.any()), times(1));
    }

    @Test
    @DisplayName("주문 옵션이 아이템 옵션과 달라 주문 생성을 실패한다.")
    void failCreateOrderCase1(){

        //given
        Item item = ItemFactory.createMacBook();
        List<CreateOrderLineItemRequestDto> orderLineItems = OrderLineItemFactory.createFailCase1CaseMackBookDto();
        CreateOrderRequestDto request =
                new CreateOrderRequestDto(orderLineItems, CreateAddressRequestFactory.create().toServiceDto(), 1L, 16000000);

        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(request);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문한 상품 이름이 상품 이름과 달라 주문 생성을 실패한다.")
    void failCreateOrderCase2(){

        //given
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));

        List<CreateOrderLineItemRequestDto> orderLineItems = OrderLineItemFactory.createFailCase2CaseMackBookDto();
        CreateOrderRequestDto request =
                new CreateOrderRequestDto(orderLineItems, CreateAddressRequestFactory.create().toServiceDto(), 1L, 16000000);

        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(request);
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
        List<CreateOrderLineItemRequestDto> orderLineItems = OrderLineItemFactory.createFailCase3CaseMackBookDto();

        CreateOrderRequestDto request =
                new CreateOrderRequestDto(orderLineItems, CreateAddressRequestFactory.create().toServiceDto(), 1L, 16000000);

        given(itemRepository.findItemByItemId(Mockito.anyLong())).willReturn(Optional.of(item));


        //when
        AbstractThrowableAssert<?, ? extends Throwable> result = Assertions.assertThatThrownBy(() -> {
            orderService.createOrder(request);
        });

        //then
        result.isInstanceOf(IllegalArgumentException.class);
        result.hasMessage("상품 옵션이 변경됐습니다.");
    }


    @Test
    void updateOrder(){

        //given
        OrderLineItem order = OrderLineItemFactory.create(3L);
        Item item = ItemFactory.createMacBook();
        given(itemRepository.findItemByShopOwnerIdAndItemId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(Optional.of(item));
        given(orderLineItemRepository.findOrderLineItemByIdAndItemId(Mockito.anyLong(), Mockito.anyLong()))
                .willReturn(Optional.of(order));

        //when
        orderService.updateOrder(1L, new UpdateOrderRequestDto(2L, 3L, OrderLineItemStatus.PREPARING_FOR_DELIVERY));

        //then
        Assertions.assertThat(order.getOrderLineItemStatus()).isSameAs(OrderLineItemStatus.PREPARING_FOR_DELIVERY);
    }
}

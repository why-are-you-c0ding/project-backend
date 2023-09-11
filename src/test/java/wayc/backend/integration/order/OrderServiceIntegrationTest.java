package wayc.backend.integration.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.OrderFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class OrderServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLineItemRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * EventListener를 단순하게 사용하는 통합테스트는 일단 도메인 이벤트가 진행된다.
     */

    @Test
    void createOrder() {

        //given
        Item saveItem = itemRepository.save(ItemFactory.createMacBook());
        List<CreateOrderLineItemRequestDto> dtoList = OrderFactory.createSuccessCaseMackBookDtoWithId(saveItem.getId());

        //when
        orderService.createOrder(1L, dtoList);

        //then
        List<OrderLineItem> result = orderRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void updateOrder(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        OrderLineItem saveOrder = orderRepository.save(OrderFactory.create(saveItem.getId()));


        //when
        orderService.updateOrder(saveShop.getOwner().getMemberId(), new UpdateOrderRequestDto(
                saveOrder.getId(),
                saveOrder.getItemId(),
                OrderStatus.COMPLETED)
        );

        //then
        Assertions.assertThat(saveOrder.getOrderStatus()).isSameAs(OrderStatus.COMPLETED);
    }
}

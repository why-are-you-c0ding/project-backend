package wayc.backend.integration.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.factory.order.OrderLineItemFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.OrderLineItemStatus;
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

    @Test
    void createOrder() {

        //given
        Item saveItem = itemRepository.save(ItemFactory.createMacBook());

        //when
        orderService.createOrder(CreateOrderRequestFactory.createSuccessCase().toServiceDto(1L));

        //then
        List<OrderLineItem> result = orderRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void updateOrder(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        OrderLineItem saveOrder = orderRepository.save(OrderLineItemFactory.create(saveItem.getId()));


        //when
        orderService.updateOrder(saveShop.getOwner().getMemberId(), new UpdateOrderRequestDto(
                saveOrder.getId(),
                saveOrder.getItemId(),
                OrderLineItemStatus.PREPARING_FOR_DELIVERY)
        );

        //then
        Assertions.assertThat(saveOrder.getOrderLineItemStatus()).isSameAs(OrderLineItemStatus.PREPARING_FOR_DELIVERY);
    }
}

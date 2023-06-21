package wayc.backend.integration.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.OrderFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;
import wayc.backend.order.application.dto.request.UpdateOrderRequestDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void createOrder() {

        //given
        Item saveItem = itemRepository.save(ItemFactory.createMacBook());
        List<CreateOrderRequestDto> dtoList = OrderFactory.createSuccessCaseMackBookDtoWithId(saveItem.getId());

        //when
        orderService.createOrder(1L, dtoList);

        //then
        List<Order> result = orderRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        //assertThat(result.get(0).getOrderStatus()).isSameAs(OrderStatus.ONGOING); 도메인 이벤트는 진행되지 않는건가?
    }

    @Test
    void updateOrder(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        Order saveOrder = orderRepository.save(OrderFactory.create(saveItem.getId()));


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

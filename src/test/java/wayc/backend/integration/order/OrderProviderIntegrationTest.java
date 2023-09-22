package wayc.backend.integration.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.Rollback;
import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.factory.order.OrderLineItemFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.application.OrderProvider;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.repository.query.dto.FindOrderResponseDto;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderProvider orderProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderLineItemRepository orderLineItemRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findDetailOrderLineItem(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        Order order = new OrderMapper().mapFrom(CreateOrderRequestFactory.createSuccessCase().toServiceDto(1L));
        orderRepository.save(order);

        //when
        FindOrderResponseDto res = orderProvider.findDetailOrderLineItem(1L, order.getId());

        //then
        assertThat(res.getOrderId()).isEqualTo(order.getId());
        assertThat(res.getItemId()).isEqualTo(saveItem.getId());
        assertThat(res.getShopId()).isEqualTo(saveShop.getId());
    }

    @Test
    void findSellerOrders(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        OrderLineItem saveOrder = orderLineItemRepository.save(OrderLineItemFactory.create(saveItem.getId()));
        payRepository.save(new Pay(saveOrder.getPayment(), saveOrder.getId()));

        //when
        FindPagingOrderResponseDto res = orderProvider.findSellerOrders(saveShop.getOwner().getMemberId(), 0);

        //then
        assertThat(res.isFinalPage()).isEqualTo(true);
        assertThat(res.getOrders().size()).isEqualTo(1);
    }

    @Rollback(value = false)
    @Test
    void findCustomerOrders(){

        //given
        Shop shop = ShopFactory.create();
        Item macBook = ItemFactory.createWithShop(shop);
        shop.getItems().add(macBook);
        shopRepository.save(shop);

        for (long i = 1;  i < 10L ; i++) {
            Order order = new OrderMapper().mapFrom(CreateOrderRequestFactory.createOrderRequest(List.of(macBook)).toServiceDto(i));
            orderRepository.save(order);
        }

        //when
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrders(1L, 0);

        assertThat(res.isFinalPage()).isEqualTo(true);
        assertThat(res.getOrders().size()).isEqualTo(1);
    }
}

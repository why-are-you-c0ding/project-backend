package wayc.backend.integration.order;

import org.junit.jupiter.api.DisplayName;
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
    private ShopRepository shopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findDetailOrderLineItem(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createWithShop(saveShop));
        Order order = new OrderMapper().mapFrom(CreateOrderRequestFactory.createOrderRequest(List.of(saveItem)).toServiceDto(1L));
        orderRepository.save(order);

        //when
        FindOrderResponseDto res = orderProvider.findDetailOrderLineItem(order.getOrderLineItems().get(0).getId());

        //then
        assertThat(res.getOrderLineItemId()).isEqualTo(order.getOrderLineItems().get(0).getId());
        assertThat(res.getItemId()).isEqualTo(saveItem.getId());
        assertThat(res.getShopId()).isEqualTo(saveShop.getId());
    }

    //@Rollback(value = false)
    @Test
    void findSellerOrders(){

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
        FindPagingOrderResponseDto res = orderProvider.findSellerOrderLineItems(shop.getOwner().getMemberId(), 0L);

        //then
        assertThat(res.isFinalPage()).isEqualTo(true);
        assertThat(res.getOrderLineItems().size()).isEqualTo(9);
    }

    @Test
    @DisplayName("마지막 페이지가 아닌 경우")
    void findCustomerOrdersNotLastPage(){

        //given
        Shop shop = ShopFactory.create();
        Item macBook = ItemFactory.createWithShop(shop);
        shop.getItems().add(macBook);
        shopRepository.save(shop);

        for (long i = 1;  i < 19L ; i++) {
            Order order = new OrderMapper().mapFrom(CreateOrderRequestFactory.createOrderRequest(List.of(macBook)).toServiceDto(1L));
            orderRepository.save(order);
        }

        //when
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrderLineItems(1L, 1L);

        assertThat(res.getOrderLineItems().size()).isEqualTo(10);
        assertThat(res.isFinalPage()).isEqualTo(false);
    }

    @Test
    @DisplayName("마지막 페이지인 경우")
    void findCustomerOrdersLastPage(){

        //given
        Shop shop = ShopFactory.create();
        Item macBook = ItemFactory.createWithShop(shop);
        shop.getItems().add(macBook);
        shopRepository.save(shop);

        for (long i = 1;  i < 12L ; i++) {
            Order order = new OrderMapper().mapFrom(CreateOrderRequestFactory.createOrderRequest(List.of(macBook)).toServiceDto(1L));
            orderRepository.save(order);
        }

        //when
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrderLineItems(1L, 1L);

        assertThat(res.getOrderLineItems().size()).isEqualTo(10);
        assertThat(res.isFinalPage()).isEqualTo(true);
    }
}

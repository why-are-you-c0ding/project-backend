package wayc.backend.integration.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import wayc.backend.factory.Item.ItemFactory;
import wayc.backend.factory.order.OrderFactory;
import wayc.backend.factory.shop.ShopFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderProvider;
import wayc.backend.order.application.dto.response.FindOrderResponseDto;
import wayc.backend.order.application.dto.response.FindPagingOrderResponseDto;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class OrderProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderProvider orderProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void findDetailOrder(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        Order saveOrder = orderRepository.save(OrderFactory.create(saveItem.getId()));
        payRepository.save(new Pay(saveOrder.getPayment(), saveOrder.getId()));

        //when
        FindOrderResponseDto res = orderProvider.findOrder(1L, saveOrder.getId());

        //then
        assertThat(res.getOrderId()).isEqualTo(saveOrder.getId());
        assertThat(res.getItemId()).isEqualTo(saveItem.getId());
        assertThat(res.getShopId()).isEqualTo(saveShop.getId());
    }

    @Test
    void findSellerOrders(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        Order saveOrder = orderRepository.save(OrderFactory.create(saveItem.getId()));
        payRepository.save(new Pay(saveOrder.getPayment(), saveOrder.getId()));

        //when
        FindPagingOrderResponseDto res = orderProvider.findSellerOrders(saveShop.getOwner().getMemberId(), 0);

        //then
        assertThat(res.isFinalPage()).isEqualTo(true);
        assertThat(res.getOrders().size()).isEqualTo(1);
    }

    @Test
    void findCustomerOrders(){

        //given
        Shop saveShop = shopRepository.save(ShopFactory.create());
        Item saveItem = itemRepository.save(ItemFactory.createItem(saveShop));
        Order saveOrder = orderRepository.save(OrderFactory.create(saveItem.getId()));
        payRepository.save(new Pay(saveOrder.getPayment(), saveOrder.getId()));

        //when
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrders(saveOrder.getOrderer().getMemberId(), 0);

        //then
        assertThat(res.isFinalPage()).isEqualTo(true);
        assertThat(res.getOrders().size()).isEqualTo(1);
    }
}

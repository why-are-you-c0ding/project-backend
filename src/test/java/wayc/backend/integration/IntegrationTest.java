package wayc.backend.integration;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.cart.domain.repository.CartRepository;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.repository.PayRepository;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

@SpringBootTest
@Transactional
public class IntegrationTest {

    @Autowired
    ShopRepository shopRepository;

    @AfterEach
    void afterEach(){
        shopRepository.deleteAll();
    }
}

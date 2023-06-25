package wayc.backend.integration;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.shop.domain.command.ShopRepository;

@SpringBootTest
@Transactional
public class IntegrationTest {

    @Autowired
    ShopRepository shopRepository;

}

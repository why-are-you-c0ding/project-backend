package wayc.backend.integration.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.service.ShopService;
import wayc.backend.shop.domain.command.ShopRepository;

import static org.assertj.core.api.Assertions.*;

public class ShopServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void createShop(){

        //when
        //이 과정을 진행하면 그대로 커밋이 되어버린다.
        shopService.registerShop(1L, "debin");

        //then
        assertThat(shopRepository.findAll().size()).isEqualTo(1);
    }
}

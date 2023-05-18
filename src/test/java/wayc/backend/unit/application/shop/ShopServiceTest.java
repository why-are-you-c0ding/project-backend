package wayc.backend.unit.application.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import wayc.backend.shop.application.service.ShopService;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.unit.application.UnitTest;

import static org.mockito.BDDMockito.*;

public class ShopServiceTest extends UnitTest {

    private ShopService shopService;

    @Mock
    private ShopRepository shopRepository;

    @BeforeEach
    void beforeEach(){
        this.shopService = new ShopService(shopRepository);
    }

    @Test
    void createShop(){

        //when
        shopService.registerShop(1L, "debin");

        //then
        verify(shopRepository, Mockito.times(1)).save(Mockito.any(Shop.class));
    }
}

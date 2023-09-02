package wayc.backend.unit.application.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import wayc.backend.shop.application.service.ShopService;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.unit.application.UnitTest;

import static org.mockito.BDDMockito.*;

public class ShopServiceTest extends UnitTest {

    @InjectMocks
    private ShopService shopService;

    @Mock
    private ShopRepository shopRepository;

}

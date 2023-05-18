package wayc.backend.integration.shop.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.provider.StockProvider;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;

public class StockProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private StockProvider stockProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void createItem(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop shop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, shop));


        //when
        stockProvider.findStock();

        //then
        Assertions.assertThat(itemRepository.findAll().size()).isEqualTo(1);
    }
}

package wayc.backend.integration.shop.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.request.RegisterStockInfoRequestDto;
import wayc.backend.shop.application.dto.request.RegisterStockRequestDto;
import wayc.backend.shop.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.shop.application.provider.StockProvider;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.ItemService;
import wayc.backend.shop.application.service.StockService;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.presentation.dto.request.FindOptionIdRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StockProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private StockProvider stockProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private StockService stockService;

    @Test
    void createItem(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop shop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, shop));

        OptionGroup optionGroup = item.getOptionGroups().get(0);
        Option option1 = optionGroup.getOptions().get(0);
        Option option2 = optionGroup.getOptions().get(1);

        OptionGroup optionGroup1 = item.getOptionGroups().get(1);
        Option option3 = optionGroup1.getOptions().get(0);
        Option option4 = optionGroup1.getOptions().get(1);

        stockService.createStock(
                new RegisterStockRequestDto(
                        List.of(
                                new RegisterStockInfoRequestDto(List.of(option1.getId(), option3.getId()), 5),
                                new RegisterStockInfoRequestDto(List.of(option1.getId(), option4.getId()), 5),
                                new RegisterStockInfoRequestDto(List.of(option2.getId(), option3.getId()), 5),
                                new RegisterStockInfoRequestDto(List.of(option2.getId(), option4.getId()), 5)
                        )
                )
        );

        //when
        FindStocksResponseDto res = stockProvider.findStock(
                List.of(
                        new FindOptionIdRequest(List.of(option1.getId(), option3.getId())),
                        new FindOptionIdRequest(List.of(option1.getId(), option4.getId())),
                        new FindOptionIdRequest(List.of(option2.getId(), option3.getId())),
                        new FindOptionIdRequest(List.of(option2.getId(), option4.getId()))
                ));

        //then
        assertThat(res.getStockList().size()).isEqualTo(4);
    }
}

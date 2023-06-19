package wayc.backend.integration.shop.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.shop.application.dto.request.FillStockRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.application.service.StockService;
import wayc.backend.shop.domain.*;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.utils.OptionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StockServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private StockRepository stockRepository;

    @Test
    void createStock(){

        //given
        RegisterItemRequestDto dto = RegisterItemRequestFactory.createSuccessCase().toServiceDto();
        Shop findShop = shopRepository.save(new Shop(1L, "shop"));
        Item item = itemRepository.save(new ItemMapper().mapOf(dto, findShop));


        OptionGroup optionGroup = item.getOptionGroups().get(0);
        Option option1 = optionGroup.getOptions().get(0); //white
        Option option2 = optionGroup.getOptions().get(1); //black

        OptionGroup optionGroup1 = item.getOptionGroups().get(1);
        Option option3 = optionGroup1.getOptions().get(0); // s
        Option option4 = optionGroup1.getOptions().get(1); // m


        //when
        stockService.fillStockUseOnlyTest(
                new FillStockRequestDto(
                        List.of(
                                new FillStockInfoRequestDto(List.of(option1.getId(), option3.getId()), 5),
                                new FillStockInfoRequestDto(List.of(option1.getId(), option4.getId()), 5),
                                new FillStockInfoRequestDto(List.of(option2.getId(), option3.getId()), 5),
                                new FillStockInfoRequestDto(List.of(option2.getId(), option4.getId()), 5)
                        )
                )
        );

        //then
        assertThat(stockRepository.findAll().size()).isEqualTo(4);
    }

    @Test
    void createStockUseOptionUtils(){
        //given
        List<OptionGroup> optionGroups = List.of(
                new OptionGroup(
                        List.of(
                                new Option("8GB", 0),
                                new Option("16GB", 80000),
                                new Option("32GB", 160000)
                        ),
                        "ram",
                        true
                ),
                new OptionGroup(
                        List.of(
                                new Option("128GB", 0),
                                new Option("512GB", 8000),
                                new Option("1024GB", 160000)
                        ),
                        "ssd",
                        false
                ),
                new OptionGroup(
                        List.of(
                                new Option("BLACK", 5000),
                                new Option("WHITE", 5000),
                                new Option("GRAY", 5000)
                        ),
                        "color",
                        false
                )
        );
        Item item = Item.builder().optionGroups(optionGroups).build();
        itemRepository.save(item);

        //when

        FillStockRequestDto requestDto = OptionUtils.createNumberOfAllOptionsToFillStock(item.getOptionGroups());
        stockService.fillStockUseOnlyTest(requestDto);

        //then
        assertThat(stockRepository.findAll().size()).isEqualTo(27);
    }
}

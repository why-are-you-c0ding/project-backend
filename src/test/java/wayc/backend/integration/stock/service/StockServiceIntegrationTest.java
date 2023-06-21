package wayc.backend.integration.stock.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.domain.*;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.utils.OptionUtils;
import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.application.service.StockService;
import wayc.backend.stock.domain.command.StockRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

        //when
        stockService.fillStockUseOnlyTest(
                new FillStockRequestDto(
                        List.of(
                                new FillStockInfoRequestDto(List.of(1L, 2L), 5),
                                new FillStockInfoRequestDto(List.of(1L, 3L), 4),
                                new FillStockInfoRequestDto(List.of(2L, 3L), 3),
                                new FillStockInfoRequestDto(List.of(3L, 4L), 2)
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

package wayc.backend.integration.stock.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.annotation.Commit;
import wayc.backend.integration.IntegrationTest;

import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;

import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.application.service.StockService;

import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.command.StockOptionRepository;
import wayc.backend.stock.domain.command.StockRepository;
import wayc.backend.stock.utils.OptionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

public class StockServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockOptionRepository stockOptionRepository;

    @Autowired
    EntityManager em;

    @Test
    void createStock(){

        //when
        stockService.fillStock(
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
        stockService.fillStock(requestDto);

        //then
        assertThat(stockRepository.findAll().size()).isEqualTo(27);
    }

    @Test
    void 재고_감소() throws InterruptedException {

        //given
        Long id = 재고_감소_테서트_전에_실행();

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decreaseStock(1, List.of(1L));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); //대기

        //then
        em.clear();
        Assertions.assertThat(stockRepository.findById(id).get().getQuantity()).isEqualTo(0);

        stockRepository.deleteAll();
        stockOptionRepository.deleteAll();
    }

    Long 재고_감소_테서트_전에_실행(){
        Stock stock = stockRepository.saveAndFlush(new Stock(100));
        stockOptionRepository.saveAndFlush(new StockOption(stock.getId(), stock.getId()));// 옵션 아이디를 stock id로 가정
        return stock.getId();
    }
}

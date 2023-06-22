package wayc.backend.integration.stock.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
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
import wayc.backend.stock.domain.query.StockQueryRepository;
import wayc.backend.stock.utils.OptionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.*;

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
    private StockQueryRepository stockQueryRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

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

    /**
     * 기존에는 한 트랜잭션에 몰려있으므로 executorService를 사용하면 다른 쓰레드에서 insert한 Row에 접근이 안됨.
     * 따라서 트랜잭션 템플릿을 사용해 트랜잭션 전파를 바꾸고 다음과 같이 진행함.
     * @throws InterruptedException
     */
    @Test
    void 재고_감소() throws InterruptedException {

        //given
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        transactionTemplate.execute(status -> {
            Stock stock = stockRepository.save(new Stock(100));
            stockOptionRepository.save(new StockOption(stock.getId(), 1L));
            return null;
        });

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try{
                    stockService.decreaseStock(1, List.of(1L));
                }
                finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); //대기

        //then

        Assertions.assertThat(stockQueryRepository.findStock(List.of(1L)).getQuantity()).isEqualTo(0);

        transactionTemplate.execute(status -> {
            stockRepository.deleteAll();
            stockOptionRepository.deleteAll();
            return null;
        });
    }
}

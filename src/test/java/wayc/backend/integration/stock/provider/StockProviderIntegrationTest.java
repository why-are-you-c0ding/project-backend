package wayc.backend.integration.stock.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.stock.application.dto.request.FindOptionIdRequestDto;
import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.application.provider.StockProvider;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.repository.StockOptionRepository;
import wayc.backend.stock.domain.repository.StockRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StockProviderIntegrationTest extends IntegrationTest {

    @Autowired
    private StockProvider stockProvider;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockOptionRepository stockOptionRepository;

    @Test
    void 옵션_id를_가지고_재고를_찾는다(){

        //given

        Stock stock_1 = stockRepository.save(new Stock(10));
        Stock stock_2 = stockRepository.save(new Stock(10));

        stockOptionRepository.save(new StockOption(stock_1, 1L));
        stockOptionRepository.save(new StockOption(stock_1, 2L));
        stockOptionRepository.save(new StockOption(stock_2, 3L));
        stockOptionRepository.save(new StockOption(stock_2, 4L));

        //when
        FindStocksResponseDto res = stockProvider.findStock(List.of(
                new FindOptionIdRequestDto(List.of(1L, 2L)),
                new FindOptionIdRequestDto(List.of(3L, 4L))
        ));

        //then
        assertThat(res.getStockList().size()).isEqualTo(2);
    }
}

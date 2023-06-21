package wayc.backend.integration.stock.provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.Item.dto.RegisterItemRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.service.ItemMapper;
import wayc.backend.shop.domain.Item;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.Shop;
import wayc.backend.shop.domain.command.ItemRepository;
import wayc.backend.shop.domain.command.ShopRepository;
import wayc.backend.stock.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.application.provider.StockProvider;
import wayc.backend.stock.application.service.StockService;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.command.StockOptionRepository;
import wayc.backend.stock.domain.command.StockRepository;
import wayc.backend.stock.presentation.dto.request.FindOptionIdRequest;

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

        stockOptionRepository.save(new StockOption(stock_1.getId(), 1L));
        stockOptionRepository.save(new StockOption(stock_1.getId(), 2L));
        stockOptionRepository.save(new StockOption(stock_2.getId(), 3L));
        stockOptionRepository.save(new StockOption(stock_2.getId(), 4L));

        //when
        FindStocksResponseDto res = stockProvider.findStock(List.of(
                new FindOptionIdRequest(List.of(1L, 2L)),
                new FindOptionIdRequest(List.of(3L, 4L))
        ));

        //then
        assertThat(res.getStockList().size()).isEqualTo(2);
    }
}

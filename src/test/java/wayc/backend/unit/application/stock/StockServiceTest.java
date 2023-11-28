package wayc.backend.unit.application.stock;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import wayc.backend.stock.application.dto.request.FillStockRequestDto;
import wayc.backend.stock.application.service.StockService;
import wayc.backend.stock.domain.Stock;
import wayc.backend.stock.domain.StockOption;
import wayc.backend.stock.domain.repository.StockOptionRepository;
import wayc.backend.stock.domain.repository.StockRepository;
import wayc.backend.stock.presentation.dto.request.FillStockInfoRequest;
import wayc.backend.stock.presentation.dto.request.FillStockRequest;
import wayc.backend.unit.application.UnitTest;

import java.util.Arrays;


import static org.mockito.BDDMockito.*;

public class StockServiceTest extends UnitTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private StockOptionRepository stockOptionRepository;

    @Test
    void 재고_등록(){

        //given
        FillStockInfoRequest req_1 = new FillStockInfoRequest(Arrays.asList(29L, 31L), 1000);
        FillStockInfoRequest req_2 = new FillStockInfoRequest(Arrays.asList(32L, 34L), 500);
        FillStockRequestDto dto = new FillStockRequest(Arrays.asList(req_1, req_2)).toServiceDto();

        given(stockRepository.save(Mockito.any(Stock.class)))
                .willReturn(new Stock(1L, 1000), new Stock(2L, 500));

        //when
        stockService.fillStock(dto);

        //then
        verify(stockRepository, Mockito.times(2)).save(Mockito.any(Stock.class));
        verify(stockOptionRepository, Mockito.times(4)).save(Mockito.any(StockOption.class));
    }
}

package wayc.backend.unit.application.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import wayc.backend.shop.application.dto.request.FillStockRequestDto;
import wayc.backend.shop.application.service.StockService;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.domain.Stock;
import wayc.backend.shop.domain.command.OptionRepository;
import wayc.backend.shop.domain.command.StockRepository;
import wayc.backend.shop.presentation.dto.request.FillStockInfoRequest;
import wayc.backend.shop.presentation.dto.request.FillStockRequest;
import wayc.backend.unit.application.UnitTest;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

public class StockServiceTest extends UnitTest {

    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private OptionRepository optionRepository;

    @BeforeEach
    void beforeEach(){
        this.stockService = new StockService(stockRepository, optionRepository);
    }

    @Test
    void createStock(){

        //given
        FillStockInfoRequest req_1 = new FillStockInfoRequest(Arrays.asList(29L, 31L), 1000);
        FillStockInfoRequest req_2 = new FillStockInfoRequest(Arrays.asList(32L, 34L), 500);
        FillStockRequestDto dto = new FillStockRequest(Arrays.asList(req_1, req_2)).toServiceDto();
        given(optionRepository.findByIdAndStatus(Mockito.anyLong())).willReturn(Optional.of(new Option("옵션1", 1000)));


        //when
        stockService.fillStock(dto);

        //then
        //stub(조회)는 구현을 깊이 테스트하는 것이므로 검증하지 않았다.
        verify(stockRepository, Mockito.times(2)).save(Mockito.any(Stock.class));
    }
}

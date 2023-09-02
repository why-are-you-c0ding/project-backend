package wayc.backend.stock.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wayc.backend.stock.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.stock.application.provider.StockProvider;
import wayc.backend.stock.application.service.StockService;
import wayc.backend.stock.domain.service.DecreaseStockService;
import wayc.backend.stock.domain.service.StockServiceFacade;
import wayc.backend.stock.presentation.dto.request.FillStockRequest;
import wayc.backend.stock.presentation.dto.request.FindStockRequest;
import wayc.backend.stock.presentation.dto.response.RegisterStockResponse;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockProvider stockProvider;

    @PostMapping
    public ResponseEntity<RegisterStockResponse> registerStock(@RequestBody FillStockRequest request){
        stockService.fillStock(request.toServiceDto());
        return new ResponseEntity(new RegisterStockResponse(), CREATED);
    }

    @GetMapping
    public ResponseEntity<FindStocksResponseDto> findStock(@GetStock FindStockRequest request){
        FindStocksResponseDto res = stockProvider.findStock(request.toServiceDto());
        return new ResponseEntity(res, OK);
    }
}

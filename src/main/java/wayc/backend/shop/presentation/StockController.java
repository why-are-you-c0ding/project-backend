package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wayc.backend.config.argumentresolver.GetStock;
import wayc.backend.shop.application.StockService;
import wayc.backend.shop.presentation.dto.request.FindStockRequest;
import wayc.backend.shop.presentation.dto.response.RegisterStockResponse;
import wayc.backend.shop.application.dto.response.find.FindStocksResponseDto;
import wayc.backend.shop.presentation.dto.request.RegisterStockRequest;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<RegisterStockResponse> registerStock(@RequestBody RegisterStockRequest request){
        stockService.create(request.toServiceDto());
        return new ResponseEntity(new RegisterStockResponse(), CREATED);
    }

    @GetMapping
    public ResponseEntity<FindStocksResponseDto> findStock(@GetStock FindStockRequest request){
        FindStocksResponseDto res = stockService.get(request.getOptionIdList());
        return new ResponseEntity(res, OK);
    }
}

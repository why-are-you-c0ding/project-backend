package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wayc.backend.config.argumentresolver.GetStock;
import wayc.backend.shop.application.StockService;
import wayc.backend.shop.presentation.dto.request.GetStockRequestDto;
import wayc.backend.shop.presentation.dto.response.PostStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStocksResponseDto;
import wayc.backend.shop.presentation.dto.request.PostStockRequestDto;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<PostStockResponseDto> createStock(@RequestBody PostStockRequestDto request){
        stockService.create(request.toServiceDto());
        return ResponseEntity.ok(new PostStockResponseDto());
    }

    @GetMapping
    public ResponseEntity<ShowStocksResponseDto> showStock(@GetStock GetStockRequestDto request){
        ShowStocksResponseDto res = stockService.get(request.getOptionIdList());
        return ResponseEntity.ok(res);
    }
}

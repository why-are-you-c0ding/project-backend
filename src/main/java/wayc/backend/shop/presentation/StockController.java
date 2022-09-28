package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wayc.backend.shop.application.StockService;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.CreateStockResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowStocksResponseDto;
import wayc.backend.shop.presentation.dto.request.PostStockRequestDto;

import java.util.Map;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;
    private final StockConverter converter;

    @PostMapping
    public ResponseEntity<CreateStockResponseDto> createStock(@RequestBody PostStockRequestDto request){
        CreateStockResponseDto res = stockService.create(new CreateStockRequestDto(request.getOptionIdList(), request.getQuantity()));
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<ShowStocksResponseDto> showStock(@RequestParam Map<String, String> request){
        ShowStocksResponseDto res = stockService.get(converter.convert(request));
        return ResponseEntity.ok(res);
    }
}

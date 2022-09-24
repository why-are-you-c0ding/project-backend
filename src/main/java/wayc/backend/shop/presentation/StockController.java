package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wayc.backend.shop.application.StockService;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;
import wayc.backend.shop.application.dto.response.CreateStockResponseDto;
import wayc.backend.shop.presentation.dto.request.PostStockRequestDto;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<CreateStockResponseDto> createStock(@RequestBody PostStockRequestDto request){
        CreateStockResponseDto res = stockService.create(new CreateStockRequestDto(request.getOptionIdList(), request.getStock()));
        return ResponseEntity.ok(res);
    }

}

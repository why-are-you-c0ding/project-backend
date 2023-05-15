package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindStocksResponseDto {

    private List<FindStockResponseDto> stockList;

    public FindStocksResponseDto(List<FindStockResponseDto> stockList) {
        this.stockList = stockList;
    }
}

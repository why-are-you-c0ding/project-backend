package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ShowStocksResponseDto {

    private List<ShowStockResponseDto> stockList;

    public ShowStocksResponseDto(List<ShowStockResponseDto> stockList) {
        this.stockList = stockList;
    }
}

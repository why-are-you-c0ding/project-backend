package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Stock;

@NoArgsConstructor
@Getter
public class FindStockResponseDto {

    private Long stockId;
    private Integer quantity;

    public FindStockResponseDto(Stock stock) {
        this.stockId = stock.getId();
        this.quantity = stock.getQuantity();
    }

    public FindStockResponseDto(Long stockId, Integer quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
    }
}

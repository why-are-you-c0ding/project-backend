package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Stock;

@NoArgsConstructor
@Getter
public class ShowStockResponseDto {

    private Long stockId;
    private Integer quantity;

    public ShowStockResponseDto(Stock stock) {
        this.stockId = stock.getId();
        this.quantity = stock.getQuantity();
    }

    public ShowStockResponseDto(Long stockId, Integer quantity) {
        this.stockId = stockId;
        this.quantity = quantity;
    }
}

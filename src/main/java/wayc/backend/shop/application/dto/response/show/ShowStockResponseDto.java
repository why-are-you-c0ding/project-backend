package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.domain.Stock;


@Getter
public class ShowStockResponseDto {

    private Long stockId;
    private Integer quantity;

    public ShowStockResponseDto(Stock stock) {
        this.stockId = stock.getId();
        this.quantity = stock.getQuantity();
    }
}

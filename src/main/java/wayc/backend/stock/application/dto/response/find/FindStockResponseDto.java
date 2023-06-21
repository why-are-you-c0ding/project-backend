package wayc.backend.stock.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.stock.domain.Stock;

@NoArgsConstructor
@Getter
public class FindStockResponseDto {

    private Integer quantity;

    public FindStockResponseDto(Integer quantity) {
        this.quantity = quantity;
    }
}

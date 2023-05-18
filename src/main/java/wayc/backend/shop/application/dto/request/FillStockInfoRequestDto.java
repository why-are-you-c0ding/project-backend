package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FillStockInfoRequestDto {

    private List<Long> optionIdList;
    private Integer quantity;


    public FillStockInfoRequestDto(List<Long> optionIdList, Integer quantity) {
        this.optionIdList = optionIdList;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FillStockInfoRequestDto{" +
                "optionIdList=" + optionIdList +
                ", quantity=" + quantity +
                '}';
    }
}

package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CreateStockInfoRequestDto {

    private List<Long> optionIdList;
    private Integer quantity;


    public CreateStockInfoRequestDto(List<Long> optionIdList, Integer quantity) {
        this.optionIdList = optionIdList;
        this.quantity = quantity;
    }
}

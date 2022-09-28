package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CreateStockRequestDto {

    private List<Long> optionIdList;
    private Integer quantity;

    public CreateStockRequestDto(List<Long> optionIdList, Integer quantity) {
        this.optionIdList = optionIdList;
        this.quantity = quantity;
    }
}
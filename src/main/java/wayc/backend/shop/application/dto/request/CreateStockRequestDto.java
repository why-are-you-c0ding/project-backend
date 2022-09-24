package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CreateStockRequestDto {

    private List<Long> optionIdList;
    private Integer stock;

    public CreateStockRequestDto(List<Long> optionIdList, Integer stock) {
        this.optionIdList = optionIdList;
        this.stock = stock;
    }
}
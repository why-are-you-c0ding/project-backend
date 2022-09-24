package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostStockRequestDto {

    @NotEmpty
    private List<Long> optionIdList;

    @Min(1000)
    private Integer stock;

    public PostStockRequestDto(List<Long> optionIdList, Integer stock) {
        this.optionIdList = optionIdList;
        this.stock = stock;
    }
}

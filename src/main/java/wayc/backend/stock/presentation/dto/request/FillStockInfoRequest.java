package wayc.backend.stock.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
public class FillStockInfoRequest {

    @NotEmpty
    private List<Long> optionIdList;

    @Min(1)
    private Integer quantity;

    public FillStockInfoRequest(List<Long> optionIdList, Integer quantity) {
        this.optionIdList = optionIdList;
        this.quantity = quantity;
    }
}

package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.request.FillStockInfoRequestDto;
import wayc.backend.shop.application.dto.request.FillStockRequestDto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FillStockRequest {

    @NotEmpty
    private List<FillStockInfoRequest> stockInfos = new ArrayList<>();

    public FillStockRequest(List<FillStockInfoRequest> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public FillStockRequestDto toServiceDto(){
        return new FillStockRequestDto(
                stockInfos
                        .stream()
                        .map(dto -> new FillStockInfoRequestDto(dto.getOptionIdList(), dto.getQuantity()))
                        .collect(Collectors.toList())
        );
    }
}

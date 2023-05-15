package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.request.RegisterStockInfoRequestDto;
import wayc.backend.shop.application.dto.request.RegisterStockRequestDto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class RegisterStockRequest {

    @NotEmpty
    private List<RegisterStockInfoRequest> stockInfos = new ArrayList<>();

    public RegisterStockRequest(List<RegisterStockInfoRequest> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public RegisterStockRequestDto toServiceDto(){
        return new RegisterStockRequestDto(
                stockInfos
                        .stream()
                        .map(dto -> new RegisterStockInfoRequestDto(dto.getOptionIdList(), dto.getQuantity()))
                        .collect(Collectors.toList())
        );
    }
}

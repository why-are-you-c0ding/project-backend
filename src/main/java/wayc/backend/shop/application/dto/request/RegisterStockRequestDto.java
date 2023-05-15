package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterStockRequestDto {

    private List<RegisterStockInfoRequestDto> stockInfos= new ArrayList<>();

    public RegisterStockRequestDto(List<RegisterStockInfoRequestDto> stockInfos) {
        this.stockInfos = stockInfos;
    }

}
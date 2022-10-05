package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CreateStockRequestDto {

    private List<CreateStockInfoRequestDto> stockInfos= new ArrayList<>();

    public CreateStockRequestDto(List<CreateStockInfoRequestDto> stockInfos) {
        this.stockInfos = stockInfos;
    }

}
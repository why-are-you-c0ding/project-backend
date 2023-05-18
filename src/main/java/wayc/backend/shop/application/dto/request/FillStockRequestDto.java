package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class FillStockRequestDto {

    private List<FillStockInfoRequestDto> stockInfos= new ArrayList<>();

    public FillStockRequestDto(List<FillStockInfoRequestDto> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public void addDto(FillStockInfoRequestDto dto){
        this.stockInfos.add(dto);
    }


    @Override
    public String toString() {
        return "FillStockRequestDto{" +
                "stockInfos=" + stockInfos +
                '}';
    }
}
package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.request.CreateStockInfoRequestDto;
import wayc.backend.shop.application.dto.request.CreateStockRequestDto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostStockRequestDto {

    @NotEmpty
    private List<PostStockInfoRequestDto> stockInfos = new ArrayList<>();

    public PostStockRequestDto(List<PostStockInfoRequestDto> stockInfos) {
        this.stockInfos = stockInfos;
    }

    public CreateStockRequestDto toServiceDto(){
        return new CreateStockRequestDto(
                stockInfos
                        .stream()
                        .map(dto -> new CreateStockInfoRequestDto(dto.getOptionIdList(), dto.getQuantity()))
                        .collect(Collectors.toList())
        );
    }
}

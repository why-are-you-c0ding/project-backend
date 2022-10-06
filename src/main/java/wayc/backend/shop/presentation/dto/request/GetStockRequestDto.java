package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetStockRequestDto {

    private List<GetOptionIdRequestDto> optionIdList = new ArrayList<>();

    public GetStockRequestDto(List<GetOptionIdRequestDto> optionIdList) {
        this.optionIdList = optionIdList;
    }
}

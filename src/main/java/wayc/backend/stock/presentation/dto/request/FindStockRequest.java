package wayc.backend.stock.presentation.dto.request;

import lombok.Getter;
import wayc.backend.stock.application.dto.request.FindOptionIdRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindStockRequest {

    private List<FindOptionIdRequest> optionIdList;

    public FindStockRequest(List<FindOptionIdRequest> optionIdList) {
        this.optionIdList = optionIdList;
    }

    public List<FindOptionIdRequestDto> toServiceDto() {
        return this.optionIdList
                .stream()
                .map(request -> request.toServiceDto())
                .collect(Collectors.toList());
    }
}

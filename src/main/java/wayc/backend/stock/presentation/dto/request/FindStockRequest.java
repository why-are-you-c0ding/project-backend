package wayc.backend.stock.presentation.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FindStockRequest {

    private List<FindOptionIdRequest> optionIdList = new ArrayList<>();

    public FindStockRequest(List<FindOptionIdRequest> optionIdList) {
        this.optionIdList = optionIdList;
    }
}

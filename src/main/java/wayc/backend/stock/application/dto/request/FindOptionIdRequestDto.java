package wayc.backend.stock.application.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FindOptionIdRequestDto {

    private List<Long> idList;

    public FindOptionIdRequestDto(List<Long> idList) {
        this.idList = idList;
    }
}

package wayc.backend.stock.presentation.dto.request;

import lombok.Getter;
import wayc.backend.stock.application.dto.request.FindOptionIdRequestDto;
import wayc.backend.stock.exception.CantResolveStockException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class FindOptionIdRequest {

    private List<Long> idList = new ArrayList<>();

    public FindOptionIdRequest(String[] idListString) {

        if(idListString.length > 1){
            throw new CantResolveStockException();
        }
        Arrays
                .stream(idListString)
                .forEach(idString -> {
                    String[] split = idString.replaceAll("\"", "").split(",");
                    Arrays.stream(split)
                            .forEach(id -> idList.add(Long.valueOf(id)));
                });
    }

    public FindOptionIdRequest(List<Long> idList) {
        this.idList = idList;
    }

    public FindOptionIdRequestDto toServiceDto() {
        return new FindOptionIdRequestDto(idList);
    }
}

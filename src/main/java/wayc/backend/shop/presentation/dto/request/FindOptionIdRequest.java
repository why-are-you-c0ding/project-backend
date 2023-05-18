package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import wayc.backend.shop.exception.CantResolveStockException;

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
}

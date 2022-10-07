package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import wayc.backend.exception.shop.CantResolveStockException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class GetOptionIdRequestDto {

    private List<Long> idList = new ArrayList<>();

    public GetOptionIdRequestDto(String[] idListString) {

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
}

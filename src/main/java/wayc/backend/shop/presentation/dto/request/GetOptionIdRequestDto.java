package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetOptionIdRequestDto {

    private List<Long> idList = new ArrayList<>();

    public GetOptionIdRequestDto(String[] idListString) {

        if(idListString.length > 1){
            throw new RuntimeException("재고 조회 형식에 맞지 않습니다.");
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

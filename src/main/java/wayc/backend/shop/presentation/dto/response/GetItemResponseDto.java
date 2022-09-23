package wayc.backend.shop.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.response.item.ShowItemResponseDto;

import java.util.List;

@NoArgsConstructor
@Getter
public class GetItemResponseDto {

    private ShowItemResponseDto o;
    private List<Object> list;
    public GetItemResponseDto(ShowItemResponseDto itemServiceRes, List<Object> objects) {
        this.o = itemServiceRes;
        this.list = objects;
    }
}

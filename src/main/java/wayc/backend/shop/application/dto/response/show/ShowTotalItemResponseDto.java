package wayc.backend.shop.application.dto.response.show;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ShowTotalItemResponseDto {

    boolean finalPage;
    private List<ShowItemsResponseDto> items = new ArrayList<>();

    public ShowTotalItemResponseDto(boolean finalPage, List<ShowItemsResponseDto> items) {
        this.finalPage = finalPage;
        this.items = items;
    }
}

package wayc.backend.shop.application.dto.response.find;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class FindPagingItemResponseDto {

    boolean finalPage;
    private List<FindItemsResponseDto> items = new ArrayList<>();

    public FindPagingItemResponseDto(boolean finalPage, List<FindItemsResponseDto> items) {
        this.finalPage = finalPage;
        this.items = items;
    }
}

package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CreateCartLineItemRequestDto {

    private Long itemId;

    private String name;

    private Integer count;

    private List<CreateOptionGroupRequestDto> cartOptionGroups = new ArrayList<>();

    private String imageUrl;

    @Builder
    public CreateCartLineItemRequestDto(Long itemId,
                                        String name,
                                        Integer count,
                                        List<CreateOptionGroupRequestDto> cartOptionGroups,
                                        String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
        this.imageUrl = imageUrl;
    }
}

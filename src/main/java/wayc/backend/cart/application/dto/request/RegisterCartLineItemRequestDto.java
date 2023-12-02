package wayc.backend.cart.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterCartLineItemRequestDto {

    private Long itemId;
    private String name;
    private Integer count;
    private List<RegisterOptionGroupRequestDto> cartOptionGroups = new ArrayList<>();
    private String imageUrl;
    private Integer price;

    @Builder
    public RegisterCartLineItemRequestDto(Long itemId,
                                          String name,
                                          Integer count,
                                          List<RegisterOptionGroupRequestDto> cartOptionGroups,
                                          String imageUrl,
                                          Integer price) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
        this.imageUrl = imageUrl;
        this.price = price;
    }
}

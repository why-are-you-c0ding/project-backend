package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchCartLineItemRequestDto {

    private Integer count;
    private Long cartLineItemId;

    public PatchCartLineItemRequestDto(Integer count, Long cartLineItemId) {
        this.count = count;
        this.cartLineItemId = cartLineItemId;
    }
}

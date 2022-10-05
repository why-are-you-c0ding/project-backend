package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeleteCartLineItemRequestDto {

    private Long cartLineItemId;

    public DeleteCartLineItemRequestDto(Long cartLineItemId) {
        this.cartLineItemId = cartLineItemId;
    }
}

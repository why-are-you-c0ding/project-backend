package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeleteCartLineItemRequest {

    private Long cartLineItemId;

    public DeleteCartLineItemRequest(Long cartLineItemId) {
        this.cartLineItemId = cartLineItemId;
    }
}

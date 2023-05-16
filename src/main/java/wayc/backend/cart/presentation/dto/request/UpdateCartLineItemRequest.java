package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCartLineItemRequest {

    private Integer count;
    private Long cartLineItemId;

    public UpdateCartLineItemRequest(Integer count, Long cartLineItemId) {
        this.count = count;
        this.cartLineItemId = cartLineItemId;
    }
}

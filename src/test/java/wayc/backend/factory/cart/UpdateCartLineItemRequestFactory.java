package wayc.backend.factory.cart;

import wayc.backend.cart.presentation.dto.request.UpdateCartLineItemRequest;

public class UpdateCartLineItemRequestFactory {

    public static UpdateCartLineItemRequest createSuccessCase(){
        return new UpdateCartLineItemRequest(9, 1L);
    }
}

package wayc.backend.factory.cart;

import wayc.backend.cart.presentation.dto.request.PatchCartLineItemRequestDto;

public class PatchCartLineItemRequestDtoFactory {

    public static PatchCartLineItemRequestDto createSuccessCase(){
        return new PatchCartLineItemRequestDto(9, 1L);
    }
}

package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.CreateAddressRequest;

public class CreateAddressRequestFactory {

    private CreateAddressRequestFactory() {}

    public static CreateAddressRequest create() {
        return new CreateAddressRequest("major", "detail", "zipcode");
    }
}

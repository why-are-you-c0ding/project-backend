package wayc.backend.factory.Item;

import wayc.backend.shop.presentation.dto.request.RegisterItemRequest;
import wayc.backend.shop.presentation.dto.request.RegisterOptionGroupRequest;
import wayc.backend.shop.presentation.dto.request.RegisterOptionRequest;

import java.util.Arrays;

public class PostItemRequestDtoFactory {

    public static RegisterItemRequest createSuccessCase(){

        RegisterOptionRequest option1 = new RegisterOptionRequest("white", 1000);
        RegisterOptionRequest option2 = new RegisterOptionRequest("black", 2000);
        RegisterOptionRequest option3 = new RegisterOptionRequest("small", 3000);
        RegisterOptionRequest option4 = new RegisterOptionRequest("large", 4000);

        RegisterOptionGroupRequest dto1 = new RegisterOptionGroupRequest(Arrays.asList(option1, option2), "color", true);
        RegisterOptionGroupRequest dto2 = new RegisterOptionGroupRequest(Arrays.asList(option3, option4), "sizee", false);

        return new RegisterItemRequest("item1", "www.google.com", "information1", Arrays.asList(dto1, dto2), "Clothing");
    }
}




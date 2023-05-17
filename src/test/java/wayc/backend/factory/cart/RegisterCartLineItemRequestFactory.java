package wayc.backend.factory.cart;

import wayc.backend.cart.presentation.dto.request.RegisterCartLineItemRequest;
import wayc.backend.cart.presentation.dto.request.RegisterCartOptionGroupRequest;
import wayc.backend.cart.presentation.dto.request.RegisterCartOptionRequest;

import java.util.Arrays;

public class RegisterCartLineItemRequestFactory {

    public static RegisterCartLineItemRequest createSuccessCase(){

        RegisterCartOptionRequest option_1 = new RegisterCartOptionRequest("basic", 20000);
        RegisterCartOptionRequest option_2 = new RegisterCartOptionRequest("yellow", 1000);
        RegisterCartOptionRequest option_3 = new RegisterCartOptionRequest("Large", 1000);

        RegisterCartOptionGroupRequest optionGroup_1 = new RegisterCartOptionGroupRequest(Arrays.asList(option_1),"기본 옵션");
        RegisterCartOptionGroupRequest optionGroup_2 = new RegisterCartOptionGroupRequest(Arrays.asList(option_3),"SIZEE");
        RegisterCartOptionGroupRequest optionGroup_3 = new RegisterCartOptionGroupRequest(Arrays.asList(option_2),"COLOR");

        return new RegisterCartLineItemRequest(29L, "멋쟁이 옷", 2, Arrays.asList(optionGroup_1, optionGroup_2, optionGroup_3), "www.image.com");
    }
}

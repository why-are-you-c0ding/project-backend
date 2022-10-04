package wayc.backend.factory.cart;

import wayc.backend.cart.presentation.dto.request.PostCartLineItemRequestDto;
import wayc.backend.cart.presentation.dto.request.PostCartOptionGroupRequestDto;
import wayc.backend.cart.presentation.dto.request.PostCartOptionRequestDto;

import java.util.Arrays;

public class PostCartLineItemRequestDtoFactory {


    public static PostCartLineItemRequestDto createSuccessCase(){

        PostCartOptionRequestDto option_1 = new PostCartOptionRequestDto("basic", 20000);
        PostCartOptionRequestDto option_2 = new PostCartOptionRequestDto("yellow", 1000);
        PostCartOptionRequestDto option_3 = new PostCartOptionRequestDto("Large", 1000);

        PostCartOptionGroupRequestDto optionGroup_1 = new PostCartOptionGroupRequestDto(Arrays.asList(option_1),"기본 옵션");
        PostCartOptionGroupRequestDto optionGroup_2 = new PostCartOptionGroupRequestDto(Arrays.asList(option_3),"SIZEE");
        PostCartOptionGroupRequestDto optionGroup_3 = new PostCartOptionGroupRequestDto(Arrays.asList(option_2),"COLOR");

        return new PostCartLineItemRequestDto(29L, "멋쟁이 옷", 2, Arrays.asList(optionGroup_1, optionGroup_2, optionGroup_3));
    }
}

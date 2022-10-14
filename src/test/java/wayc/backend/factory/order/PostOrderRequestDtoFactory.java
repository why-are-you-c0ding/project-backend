package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.PostOrderLineItemRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionGroupRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
;


import java.util.Arrays;

public class PostOrderRequestDtoFactory {

    public static PostOrderRequestDto createSuccessCase(){

        PostOrderOptionRequestDto option1 = new PostOrderOptionRequestDto("white", 1000);
        PostOrderOptionRequestDto option2 = new PostOrderOptionRequestDto("black", 2000);
        PostOrderOptionRequestDto option3 = new PostOrderOptionRequestDto("small", 3000);
        PostOrderOptionRequestDto option4 = new PostOrderOptionRequestDto("large", 4000);

        PostOrderOptionGroupRequestDto color = new PostOrderOptionGroupRequestDto("color", Arrays.asList(option1, option2));
        PostOrderOptionGroupRequestDto sizee = new PostOrderOptionGroupRequestDto("sizee", Arrays.asList(option3, option4));

        PostOrderLineItemRequestDto items = new PostOrderLineItemRequestDto(1L, "멋쟁이 옷", 3, Arrays.asList(color, sizee));

        return new PostOrderRequestDto(1L, Arrays.asList(items));
    }
}




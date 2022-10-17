package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionGroupRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionRequestDto;
;


import java.util.Arrays;
import java.util.List;

public class PostOrderRequestDtoFactory {

    public static List<PostOrderRequestDto> createSuccessCase(){

        PostOrderOptionRequestDto option1 = new PostOrderOptionRequestDto("white", 1000);
        PostOrderOptionRequestDto option2 = new PostOrderOptionRequestDto("black", 2000);
        PostOrderOptionRequestDto option3 = new PostOrderOptionRequestDto("small", 3000);
        PostOrderOptionRequestDto option4 = new PostOrderOptionRequestDto("large", 4000);

        PostOrderOptionGroupRequestDto color = new PostOrderOptionGroupRequestDto("color", Arrays.asList(option1, option2));
        PostOrderOptionGroupRequestDto sizee = new PostOrderOptionGroupRequestDto("sizee", Arrays.asList(option3, option4));

        return Arrays.asList(new PostOrderRequestDto(1L, "멋쟁이 옷", 3, Arrays.asList(color, sizee)));
    }
}




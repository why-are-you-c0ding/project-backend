package wayc.backend.factory.order;

import wayc.backend.order.presentation.dto.request.PostAddressRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionGroupRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderOptionRequestDto;


import java.util.Arrays;
import java.util.List;

public class RegisterOrderRequestDtoFactory {

    public static List<PostOrderRequestDto> createSuccessCase(){

        PostOrderOptionRequestDto option1 = new PostOrderOptionRequestDto("white", 1000);
        PostOrderOptionRequestDto option4 = new PostOrderOptionRequestDto("large", 4000);

        PostOrderOptionGroupRequestDto color = new PostOrderOptionGroupRequestDto("color", option1);
        PostOrderOptionGroupRequestDto sizee = new PostOrderOptionGroupRequestDto("sizee", option4);

        PostAddressRequestDto address = new PostAddressRequestDto("서울특별시 멋진구 멋진동", "멋진아파트 111동 111호", "01099");

        return Arrays.asList(new PostOrderRequestDto(1L, "멋쟁이 옷", 3, 40000,Arrays.asList(color, sizee), address));
    }
}




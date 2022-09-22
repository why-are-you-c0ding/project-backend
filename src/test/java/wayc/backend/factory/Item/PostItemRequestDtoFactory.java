package wayc.backend.factory.Item;

import wayc.backend.shop.presentation.dto.request.PostItemRequestDto;
import wayc.backend.shop.presentation.dto.request.PostOptionGroupRequestDto;
import wayc.backend.shop.presentation.dto.request.PostOptionRequestDto;

import java.util.Arrays;

public class PostItemRequestDtoFactory {

    public static PostItemRequestDto createSuccessCase(){

        PostOptionRequestDto option1 = new PostOptionRequestDto("option1", 1000);
        PostOptionRequestDto option2 = new PostOptionRequestDto("option2", 2000);
        PostOptionRequestDto option3 = new PostOptionRequestDto("option3", 3000);
        PostOptionRequestDto option4 = new PostOptionRequestDto("option4", 4000);

        PostOptionGroupRequestDto dto1 = new PostOptionGroupRequestDto(Arrays.asList(option1, option2));
        PostOptionGroupRequestDto dto2 = new PostOptionGroupRequestDto(Arrays.asList(option3, option4));

        return new PostItemRequestDto("item1",Arrays.asList(dto1, dto2));
    }
}




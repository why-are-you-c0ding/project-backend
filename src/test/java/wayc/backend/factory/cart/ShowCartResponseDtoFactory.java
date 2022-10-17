package wayc.backend.factory.cart;

import wayc.backend.cart.application.dto.response.ShowCartLineItemResponseDto;
import wayc.backend.cart.application.dto.response.ShowCartResponseDto;
import wayc.backend.cart.application.dto.response.ShowCartOptionGroupResponseDto;
import wayc.backend.cart.application.dto.response.ShowCartOptionResponseDto;

import java.util.Arrays;

public class ShowCartResponseDtoFactory {


    public static ShowCartResponseDto createSuccessCase(){

        ShowCartOptionResponseDto option_1 = ShowCartOptionResponseDto.builder().id(1L).name("Large").price(2000).build();
        ShowCartOptionResponseDto option_2 = ShowCartOptionResponseDto.builder().id(2L).name("Black").price(1000).build();
        ShowCartOptionResponseDto option_3 = ShowCartOptionResponseDto.builder().id(3L).name("Large").price(2000).build();


        ShowCartOptionGroupResponseDto optionGroup_1 =
                ShowCartOptionGroupResponseDto.builder().id(11L).name("기초 옵션").cartOptions(Arrays.asList(option_1)).build();
        ShowCartOptionGroupResponseDto optionGrout_2 =
                ShowCartOptionGroupResponseDto.builder().id(12L).name("Color").cartOptions(Arrays.asList(option_2)).build();
        ShowCartOptionGroupResponseDto optionGroup_3 =
                ShowCartOptionGroupResponseDto.builder().id(13L).name("Sizee").cartOptions(Arrays.asList(option_3)).build();

        ShowCartLineItemResponseDto cartLineItem = ShowCartLineItemResponseDto.builder().id(20L).itemId(29L).name("멋쟁이 옷")
                .imageUrl("www.image.url")
                .count(2)
                .cartOptionGroups(Arrays.asList(
                optionGroup_1, optionGrout_2, optionGroup_3
        )).build();

        return new ShowCartResponseDto(Arrays.asList(cartLineItem));

    }
}

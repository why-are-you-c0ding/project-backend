package wayc.backend.factory.cart;

import wayc.backend.cart.application.dto.response.FindCartLineItemResponseDto;
import wayc.backend.cart.application.dto.response.FindCartResponseDto;
import wayc.backend.cart.application.dto.response.FindCartOptionGroupResponseDto;
import wayc.backend.cart.application.dto.response.FindCartOptionResponseDto;

import java.util.Arrays;

public class FindCartResponseDtoFactory {


    public static FindCartResponseDto createSuccessCase(){

        FindCartOptionResponseDto option_1 = FindCartOptionResponseDto.builder().name("Large").price(2000).build();
        FindCartOptionResponseDto option_2 = FindCartOptionResponseDto.builder().name("Black").price(1000).build();
        FindCartOptionResponseDto option_3 = FindCartOptionResponseDto.builder().name("Large").price(2000).build();


        FindCartOptionGroupResponseDto optionGroup_1 =
                FindCartOptionGroupResponseDto.builder().id(11L).name("기초 옵션").cartOption(option_1).build();
        FindCartOptionGroupResponseDto optionGrout_2 =
                FindCartOptionGroupResponseDto.builder().id(12L).name("Color").cartOption(option_2).build();
        FindCartOptionGroupResponseDto optionGroup_3 =
                FindCartOptionGroupResponseDto.builder().id(13L).name("Sizee").cartOption(option_3).build();

        FindCartLineItemResponseDto cartLineItem = FindCartLineItemResponseDto.builder().id(20L).itemId(29L).name("멋쟁이 옷")
                .imageUrl("www.image.url")
                .count(2)
                .cartOptionGroups(Arrays.asList(
                optionGroup_1, optionGrout_2, optionGroup_3
        )).build();

        return new FindCartResponseDto(Arrays.asList(cartLineItem));

    }
}

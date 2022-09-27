package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowOptionGroupResponseDtoFactory {

    public static List<ShowOptionGroupResponseDto> createSuccessCaseDto(){

        ShowOptionResponseDto optionRes1 = new ShowOptionResponseDto(10L, "white", 1000);
        ShowOptionResponseDto optionRes2 = new ShowOptionResponseDto(11L, "black", 1000);
        ShowOptionResponseDto optionRes3 = new ShowOptionResponseDto(12L, "small", 1000);
        ShowOptionResponseDto optionRes4 = new ShowOptionResponseDto(13L, "superLarge", 1000);

        ShowOptionGroupResponseDto optionGroup1 =
                new ShowOptionGroupResponseDto(20L, "color", Arrays.asList(optionRes1, optionRes2), true);

        ShowOptionGroupResponseDto optionGroup2 =
                new ShowOptionGroupResponseDto(21L, "size", Arrays.asList(optionRes3, optionRes4), false);

        return Arrays.asList(optionGroup1, optionGroup2);
    }
}

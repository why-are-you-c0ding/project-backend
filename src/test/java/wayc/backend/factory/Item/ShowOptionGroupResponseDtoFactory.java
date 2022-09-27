package wayc.backend.factory.Item;

import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionResponseDto;

import java.util.Arrays;
import java.util.List;

public class ShowOptionGroupResponseDtoFactory {

    public static List<ShowOptionGroupResponseDto> createSuccessCaseDto(){

        ShowOptionResponseDto optionRes1 = new ShowOptionResponseDto(10L, "optionRes1", 1000);
        ShowOptionResponseDto optionRes2 = new ShowOptionResponseDto(11L, "optionRes2", 1000);
        ShowOptionResponseDto optionRes3 = new ShowOptionResponseDto(12L, "optionRes3", 1000);
        ShowOptionResponseDto optionRes4 = new ShowOptionResponseDto(13L, "optionRes4", 1000);

        ShowOptionGroupResponseDto optionGroup1 =
                new ShowOptionGroupResponseDto(20L, "optionGroup1", Arrays.asList(optionRes1, optionRes2), true);

        ShowOptionGroupResponseDto optionGroup2 =
                new ShowOptionGroupResponseDto(21L, "optionGroup2", Arrays.asList(optionRes3, optionRes4), false);

        return Arrays.asList(optionGroup1, optionGroup2);
    }
}

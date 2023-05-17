package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindOrderOptionGroupResponseDto {

    private String name;
    private FindOrderOptionResponseDto option;

    public FindOrderOptionGroupResponseDto(String name, FindOrderOptionResponseDto option) {
        this.name = name;
        this.option = option;
    }
}

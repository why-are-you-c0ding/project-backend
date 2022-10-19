package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Order;

@NoArgsConstructor
@Getter
public class ShowOrderOptionGroupResponseDto {

    private String name;
    private ShowOrderOptionResponseDto option;

    public ShowOrderOptionGroupResponseDto(String name, ShowOrderOptionResponseDto option) {
        this.name = name;
        this.option = option;
    }
}

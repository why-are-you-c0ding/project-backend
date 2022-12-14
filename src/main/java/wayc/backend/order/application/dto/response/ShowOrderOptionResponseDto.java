package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Order;

@NoArgsConstructor
@Getter
public class ShowOrderOptionResponseDto {

    private String name;

    public ShowOrderOptionResponseDto(String name) {
        this.name = name;
    }
}

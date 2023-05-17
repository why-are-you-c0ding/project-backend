package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindOrderOptionResponseDto {

    private String name;

    public FindOrderOptionResponseDto(String name) {
        this.name = name;
    }
}

package wayc.backend.order.domain.repository.query.dto;

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

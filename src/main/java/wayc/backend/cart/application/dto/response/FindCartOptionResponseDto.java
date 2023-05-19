package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindCartOptionResponseDto {

    private String name;

    private Integer price;

    @Builder
    public FindCartOptionResponseDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}

package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShowOptionResponseDto {

    private Long id;

    private String name;

    private Integer price;

    @Builder
    public ShowOptionResponseDto(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

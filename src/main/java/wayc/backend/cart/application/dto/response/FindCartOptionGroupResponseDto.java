package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.CartOptionGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class FindCartOptionGroupResponseDto {

    private Long id;
    private FindCartOptionResponseDto cartOption;
    private String name;

    @Builder
    public FindCartOptionGroupResponseDto(Long id, FindCartOptionResponseDto cartOption, String name) {
        this.id = id;
        this.cartOption = cartOption;
        this.name = name;
    }

    public static FindCartOptionGroupResponseDto toOptionGroupResponseDto(CartOptionGroup cartOptionGroup){
        return FindCartOptionGroupResponseDto
                .builder()
                .id(cartOptionGroup.getId())
                .name(cartOptionGroup.getName())
                .cartOption(
                        new FindCartOptionResponseDto(
                                cartOptionGroup.getCartOption().getOptionName(),
                                cartOptionGroup.getCartOption().getPrice()
                        )
                ).build();
    }
}

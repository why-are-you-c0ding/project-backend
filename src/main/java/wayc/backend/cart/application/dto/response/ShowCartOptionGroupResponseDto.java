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
public class ShowCartOptionGroupResponseDto {

    private Long id;
    private List<ShowCartOptionResponseDto> cartOptions = new ArrayList<>();
    private String name;

    @Builder
    public ShowCartOptionGroupResponseDto(Long id, List<ShowCartOptionResponseDto> cartOptions, String name) {
        this.id = id;
        this.cartOptions = cartOptions;
        this.name = name;
    }

    public static ShowCartOptionGroupResponseDto toOptionGroupResponseDto(CartOptionGroup cartOptionGroup){
        return ShowCartOptionGroupResponseDto
                .builder()
                .id(cartOptionGroup.getId())
                .name(cartOptionGroup.getName())
                .cartOptions(cartOptionGroup.getCartOptions()
                        .stream()
                        .map(cartOption ->
                                ShowCartOptionResponseDto
                                        .builder()
                                        .id(cartOption.getId())
                                        .name(cartOption.getName())
                                        .price(cartOption.getPrice())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}

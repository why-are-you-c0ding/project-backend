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
    private List<FindCartOptionResponseDto> cartOptions = new ArrayList<>();
    private String name;

    @Builder
    public FindCartOptionGroupResponseDto(Long id, List<FindCartOptionResponseDto> cartOptions, String name) {
        this.id = id;
        this.cartOptions = cartOptions;
        this.name = name;
    }

    public static FindCartOptionGroupResponseDto toOptionGroupResponseDto(CartOptionGroup cartOptionGroup){
        return FindCartOptionGroupResponseDto
                .builder()
                .id(cartOptionGroup.getId())
                .name(cartOptionGroup.getName())
                .cartOptions(cartOptionGroup.getCartOptions()
                        .stream()
                        .map(cartOption ->
                                FindCartOptionResponseDto
                                        .builder()
                                        .id(cartOption.getId())
                                        .name(cartOption.getName())
                                        .price(cartOption.getPrice())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}

package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.CartOption;
import wayc.backend.cart.domain.CartOptionGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowOptionGroupResponseDto {


    private Long id;

    private List<ShowOptionResponseDto> cartOptions = new ArrayList<>();

    private String name;

    @Builder
    public ShowOptionGroupResponseDto(Long id, List<ShowOptionResponseDto> cartOptions, String name) {
        this.id = id;
        this.cartOptions = cartOptions;
        this.name = name;
    }

    public static ShowOptionGroupResponseDto toOptionGroupResponseDto(CartOptionGroup cartOptionGroup){
        return ShowOptionGroupResponseDto
                .builder()
                .id(cartOptionGroup.getId())
                .name(cartOptionGroup.getName())
                .cartOptions(cartOptionGroup.getCartOptions()
                        .stream()
                        .map(cartOption -> ShowOptionResponseDto
                                        .builder()
                                        .id(cartOption.getId())
                                        .name(cartOption.getName())
                                .price(cartOption.getPrice())
                                .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}

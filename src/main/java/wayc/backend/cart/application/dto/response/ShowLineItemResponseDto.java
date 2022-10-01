package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.Cart;
import wayc.backend.cart.domain.CartLineItem;
import wayc.backend.cart.domain.CartOptionGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowLineItemResponseDto {


    private Long id;

    private Long itemId;

    private String name;

    private Integer count;

    private List<ShowOptionGroupResponseDto> cartOptionGroups = new ArrayList<>();

    @Builder
    public ShowLineItemResponseDto(Long id, Long itemId, String name, Integer count, List<ShowOptionGroupResponseDto> cartOptionGroups) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
    }

    public static ShowLineItemResponseDto toLineItemResponseDto(CartLineItem cartLineItem){
        return ShowLineItemResponseDto
                .builder()
                .id(cartLineItem.getId())
                .itemId(cartLineItem.getItemId())
                .name(cartLineItem.getName())
                .count(cartLineItem.getCount())
                .cartOptionGroups(
                        cartLineItem
                                .getCartOptionGroups()
                                .stream()
                                .map(cartOptionGroup ->
                                        ShowOptionGroupResponseDto.toOptionGroupResponseDto(cartOptionGroup)
                                )
                                .collect(Collectors.toList()))
                .build();
    }
}

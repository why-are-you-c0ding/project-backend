package wayc.backend.cart.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.domain.CartLineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowCartLineItemResponseDto {

    private Long id;

    private Long itemId;

    private String name;

    private Integer count;

    private List<ShowCartOptionGroupResponseDto> cartOptionGroups = new ArrayList<>();

    @Builder
    public ShowCartLineItemResponseDto(Long id, Long itemId, String name, Integer count, List<ShowCartOptionGroupResponseDto> cartOptionGroups) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
    }

    public static ShowCartLineItemResponseDto toCartLineItemResponseDto(CartLineItem cartLineItem){
        return ShowCartLineItemResponseDto
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
                                        ShowCartOptionGroupResponseDto.toOptionGroupResponseDto(cartOptionGroup)
                                )
                                .collect(Collectors.toList()))
                .build();
    }
}

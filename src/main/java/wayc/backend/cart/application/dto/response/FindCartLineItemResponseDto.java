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
public class FindCartLineItemResponseDto {

    private Long id;

    private Long itemId;

    private String name;

    private Integer count;

    private List<FindCartOptionGroupResponseDto> cartOptionGroups = new ArrayList<>();

    private String imageUrl;

    @Builder
    public FindCartLineItemResponseDto(Long id, Long itemId, String name, Integer count, List<FindCartOptionGroupResponseDto> cartOptionGroups, String imageUrl) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
        this.imageUrl = imageUrl;
    }

    public static FindCartLineItemResponseDto toCartLineItemResponseDto(CartLineItem cartLineItem){
        return FindCartLineItemResponseDto
                .builder()
                .id(cartLineItem.getId())
                .itemId(cartLineItem.getItemId())
                .name(cartLineItem.getName())
                .count(cartLineItem.getCount())
                .imageUrl(cartLineItem.getImageUrl())
                .cartOptionGroups(
                        cartLineItem
                                .getCartOptionGroups()
                                .stream()
                                .map(cartOptionGroup ->
                                        FindCartOptionGroupResponseDto.toOptionGroupResponseDto(cartOptionGroup)
                                )
                                .collect(Collectors.toList()))
                .build();
    }
}

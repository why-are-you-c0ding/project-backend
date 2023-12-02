package wayc.backend.cart.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.RegisterCartLineItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class RegisterCartLineItemRequest {

    private Long itemId;
    private String name;
    private Integer count;
    private String imageUrl;
    private Integer price;

    @Valid
    @NotEmpty
    private List<RegisterCartOptionGroupRequest> cartOptionGroups = new ArrayList<>();

    public RegisterCartLineItemRequest(Long itemId,
                                       String name,
                                       Integer count,
                                       List<RegisterCartOptionGroupRequest> optionGroups,
                                       String imageUrl,
                                       Integer price) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = optionGroups;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public RegisterCartLineItemRequestDto toServiceDto(){
        return RegisterCartLineItemRequestDto
                .builder()
                .itemId(itemId)
                .name(name)
                .count(count)
                .price(price)
                .cartOptionGroups(
                        cartOptionGroups
                                .stream()
                                .map(RegisterCartOptionGroupRequest::toServiceDto)
                                .collect(Collectors.toList())
                )
                .imageUrl(imageUrl)
                .build();
    }
}

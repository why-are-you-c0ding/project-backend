package wayc.backend.cart.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.CreateCartLineItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostCartLineItemRequestDto {

    private Long itemId;
    private String name;
    private Integer count;
    private String imageUrl;

    @Valid
    @NotEmpty
    private List<PostCartOptionGroupRequestDto> cartOptionGroups = new ArrayList<>();

    public PostCartLineItemRequestDto(Long itemId, String name, Integer count, List<PostCartOptionGroupRequestDto> optionGroups, String imageUrl) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = optionGroups;
        this.imageUrl = imageUrl;
    }

    public CreateCartLineItemRequestDto toServiceDto(){
        return CreateCartLineItemRequestDto
                .builder()
                .itemId(itemId)
                .name(name)
                .count(count)
                .cartOptionGroups(
                        cartOptionGroups
                                .stream()
                                .map(PostCartOptionGroupRequestDto::toServiceDto)
                                .collect(Collectors.toList())
                )
                .imageUrl(imageUrl)
                .build();
    }
}

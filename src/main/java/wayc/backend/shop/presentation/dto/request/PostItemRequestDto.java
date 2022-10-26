package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.application.dto.request.CreateItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostItemRequestDto {

    @Size(min = 4)
    private String itemName;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String information;

    @NotBlank
    private String category;

    @Valid
    @NotEmpty
    private List<PostOptionGroupRequestDto> optionGroups;

    public CreateItemRequestDto toServiceDto(){
        return new CreateItemRequestDto(optionGroups, itemName, imageUrl, information, category);
    }

    public PostItemRequestDto(String itemName, String imageUrl, String information, List<PostOptionGroupRequestDto> optionGroupRequests, String category) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.information = information;
        this.optionGroups = optionGroupRequests;
        this.category = category;
    }
}

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

    @Valid
    @NotEmpty
    private List<PostOptionGroupRequestDto> optionGroupRequests;

    public CreateItemRequestDto toServiceDto(){
        return new CreateItemRequestDto(optionGroupRequests, itemName, imageUrl);
    }

    public PostItemRequestDto(String itemName, String imageUrl, List<PostOptionGroupRequestDto> optionGroupRequests) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.optionGroupRequests = optionGroupRequests;
    }
}

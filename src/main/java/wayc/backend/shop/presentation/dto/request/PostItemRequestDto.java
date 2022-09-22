package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.application.dto.request.CreateItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostItemRequestDto {

    @Size(min = 4)
    private String itemName;

    @Valid
    @NotEmpty
    private List<PostOptionGroupRequestDto> optionGroupRequests;

    public CreateItemRequestDto toServiceDto(){
        return new CreateItemRequestDto(optionGroupRequests, itemName);
    }

    public PostItemRequestDto(String itemName, List<PostOptionGroupRequestDto> optionGroupRequests) {
        this.itemName = itemName;
        this.optionGroupRequests = optionGroupRequests;
    }
}

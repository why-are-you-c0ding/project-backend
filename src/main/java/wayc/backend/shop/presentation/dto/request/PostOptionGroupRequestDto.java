package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostOptionGroupRequestDto {

    @Valid
    @NotEmpty
    private List<PostOptionRequestDto> optionRequests;

    @Size(min = 5)
    private String optionGroupName;

    public PostOptionGroupRequestDto(List<PostOptionRequestDto> optionRequests, String optionGroupName) {
        this.optionRequests = optionRequests;
        this.optionGroupName = optionGroupName;
    }
}

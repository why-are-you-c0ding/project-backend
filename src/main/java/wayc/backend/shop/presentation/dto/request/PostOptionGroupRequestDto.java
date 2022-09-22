package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostOptionGroupRequestDto {

    @Valid
    @NotEmpty
    private List<PostOptionRequestDto> optionRequests;

    public PostOptionGroupRequestDto(List<PostOptionRequestDto> optionRequests) {
        this.optionRequests = optionRequests;
    }
}

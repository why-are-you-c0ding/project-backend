package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
public class PostOptionGroupRequestDto {

    @Valid
    @NotEmpty
    private List<PostOptionRequestDto> options;

    @Size(min = 5)
    private String optionGroupName;

    @NotNull
    private Boolean basic;

    public PostOptionGroupRequestDto(List<PostOptionRequestDto> optionRequests, String optionGroupName, Boolean basic) {
        this.options = optionRequests;
        this.optionGroupName = optionGroupName;
        this.basic = basic;
    }
}

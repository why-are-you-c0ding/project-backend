package wayc.backend.shop.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.shop.application.dto.request.RegisterOptionGroupRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

import static java.util.stream.Collectors.*;

@NoArgsConstructor
@Getter
public class RegisterOptionGroupRequest {

    @Valid
    @NotEmpty
    private List<RegisterOptionRequest> options;

    @Size(min = 5)
    private String optionGroupName;

    public RegisterOptionGroupRequest(List<RegisterOptionRequest> optionRequests,
                                      String optionGroupName) {
        this.options = optionRequests;
        this.optionGroupName = optionGroupName;
    }

    public RegisterOptionGroupRequestDto toServiceDto(){
        return new RegisterOptionGroupRequestDto(
                options
                        .stream()
                        .map(option -> option.toServiceDto())
                        .collect(toList()),
                optionGroupName
        );
    }
}

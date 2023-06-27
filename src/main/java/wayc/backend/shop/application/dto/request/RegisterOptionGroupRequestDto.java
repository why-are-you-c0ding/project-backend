package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RegisterOptionGroupRequestDto {

    private List<RegisterOptionRequestDto> optionRequests;
    private String optionGroupName;

    public RegisterOptionGroupRequestDto(List<RegisterOptionRequestDto> dtos,
                                         String optionGroupName) {
        this.optionGroupName = optionGroupName;
        this.optionRequests = dtos;
    }
}

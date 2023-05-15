package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.presentation.dto.request.RegisterOptionRequest;  //의존성이 반대로 흘러버림.

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class RegisterOptionGroupRequestDto {

    private List<RegisterOptionRequestDto> optionRequests;
    private String optionGroupName;
    private Boolean basic;

    public RegisterOptionGroupRequestDto(List<RegisterOptionRequest> dtos, String optionGroupName, Boolean basic) {
        this.optionRequests = dtos
                .stream()
                .map(dto -> new RegisterOptionRequestDto(dto.getOptionName(), dto.getPrice()))
                .collect(Collectors.toList());
        this.optionGroupName = optionGroupName;
        this.basic = basic;
    }
}

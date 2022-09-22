package wayc.backend.shop.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.shop.presentation.dto.request.PostOptionRequestDto;  //의존성이 반대로 흘러버림.

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CreateOptionGroupRequestDto {

    private List<CreateOptionRequestDto> optionRequests;

    public CreateOptionGroupRequestDto(List<PostOptionRequestDto> dtos) {
        this.optionRequests = dtos
                .stream()
                .map(dto -> new CreateOptionRequestDto(dto.getOptionName(), dto.getPrice()))
                .collect(Collectors.toList());
    }

//    public CreateOptionGroupRequestDto(List<CreateOptionRequestDto> optionRequests) {
//        this.optionRequests = optionRequests;
//    }
}

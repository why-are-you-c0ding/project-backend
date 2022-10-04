package wayc.backend.cart.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.cart.application.dto.request.CreateOptionGroupRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostCartOptionGroupRequestDto {

    private List<PostCartOptionRequestDto> cartOptions = new ArrayList<>();

    private String name;

    public PostCartOptionGroupRequestDto(List<PostCartOptionRequestDto> cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }

    public CreateOptionGroupRequestDto toServiceDto(){
        return CreateOptionGroupRequestDto
                .builder()
                .name(name)
                .cartOptions(
                        cartOptions
                                .stream()
                                .map(PostCartOptionRequestDto::toServiceDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostOrderRequestDto {

    @NotNull
    private Long itemId;

    @NotBlank
    private String name;

    @Min(0)
    private Integer count;

    @Valid
    @NotEmpty
    private List<PostOrderOptionGroupRequestDto> orderOptionGroups = new ArrayList<>();

    public PostOrderRequestDto(Long itemId, String name, Integer count, List<PostOrderOptionGroupRequestDto> orderOptionGroupsDto) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroups = orderOptionGroupsDto;
    }

    public CreateOrderRequestDto toServiceDto(){
        return new CreateOrderRequestDto(
                itemId,
                name,
                count,
                orderOptionGroups
                        .stream()
                        .map(optionGroupsDto -> optionGroupsDto.toServiceDto())
                        .collect(Collectors.toList())
        );
    }
}
package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderLineItemRequestDto {

    private Long itemId;

    private String name;

    private Integer count;

    private List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto = new ArrayList<>();

    public CreateOrderLineItemRequestDto(Long itemId, String name, Integer count, List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroupsDto = orderOptionGroupsDto;
    }
}

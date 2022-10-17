package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequestDto {

    private Long itemId;

    private String name;

    private Integer count;

    private List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto = new ArrayList<>();

    public CreateOrderRequestDto(Long itemId, String name, Integer count, List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroupsDto = orderOptionGroupsDto;
    }
}

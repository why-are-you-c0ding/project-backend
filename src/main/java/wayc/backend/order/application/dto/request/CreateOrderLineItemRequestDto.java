package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Address;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderLineItemRequestDto {

    private Long itemId;

    private String name;

    private Integer count;

    private Integer payment;

    private List<CreateOrderOptionGroupRequestDto> orderOptionGroups = new ArrayList<>();


    public CreateOrderLineItemRequestDto(Long itemId,
                                         String name,
                                         Integer count,
                                         Integer payment,
                                         List<CreateOrderOptionGroupRequestDto> orderOptionGroupsDto) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.orderOptionGroups = orderOptionGroupsDto;
        this.payment = payment;
    }
}

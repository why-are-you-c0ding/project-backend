package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor
public class CreateOrderLineItemRequest {

    @NotNull
    private Long itemId;

    @NotBlank
    private String name;

    @Min(0)
    private Integer count;

    @NotNull
    private Integer price;

    @Valid
    @NotEmpty
    private List<CreateOrderOptionGroupRequest> orderOptionGroups = new ArrayList<>();

    public CreateOrderLineItemRequest(Long itemId,
                                      String name,
                                      Integer count,
                                      Integer price,
                                      List<CreateOrderOptionGroupRequest> orderOptionGroups) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.price = price;
        this.orderOptionGroups = orderOptionGroups;
    }

    public CreateOrderLineItemRequestDto toServiceDto() {
        return new CreateOrderLineItemRequestDto(
                itemId,
                name,
                count,
                price,
                orderOptionGroups
                        .stream()
                        .map(orderOptionGroup -> orderOptionGroup.toServiceDto())
                        .collect(toList())
        );
    }
}

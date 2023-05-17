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
public class CreateOrderRequest {

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

    @NotNull
    private CreateAddressRequest address;

    public CreateOrderRequest(Long itemId,
                              String name,
                              Integer count,
                              Integer price,
                              List<CreateOrderOptionGroupRequest> orderOptionGroups,
                              CreateAddressRequest address) {
        this.itemId = itemId;
        this.name = name;
        this.count = count;
        this.price = price;
        this.orderOptionGroups = orderOptionGroups;
        this.address = address;
    }

    public CreateOrderRequestDto toServiceDto(){
        return new CreateOrderRequestDto(
                itemId,
                name,
                count,
                orderOptionGroups
                        .stream()
                        .map(optionGroupsDto -> optionGroupsDto.toServiceDto())
                        .collect(Collectors.toList()),
                address.toServiceDto(),
                price
        );
    }
}

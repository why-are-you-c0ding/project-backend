package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderLineItemRequestDto;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {

    @Valid
    @NotEmpty
    private List<CreateOrderLineItemRequest> orderLineItems;

    @NotNull
    private CreateAddressRequest address;

    private Integer totalPayment;

    public CreateOrderRequest(List<CreateOrderLineItemRequest> orderLineItems,
                              CreateAddressRequest address,
                              Integer totalPayment) {
        this.orderLineItems = orderLineItems;
        this.address = address;
        this.totalPayment = totalPayment;
    }

    public CreateOrderRequestDto toServiceDto(Long ordererId){
        return new CreateOrderRequestDto(
                orderLineItems.stream()
                        .map(itemDto -> itemDto.toServiceDto())
                        .collect(Collectors.toList()),
                address.toServiceDto(),
                ordererId,
                totalPayment
        );
    }
}

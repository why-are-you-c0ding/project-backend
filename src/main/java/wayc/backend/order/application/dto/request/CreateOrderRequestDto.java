package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Address;

import java.util.List;

@Getter
public class CreateOrderRequestDto {

    private final List<CreateOrderLineItemRequestDto> orderLineItems;
    private final CreateAddressRequestDto address;
    private final Long ordererId;
    private final Integer totalPayment;

    public CreateOrderRequestDto(List<CreateOrderLineItemRequestDto> orderLineItems,
                                 CreateAddressRequestDto address,
                                 Long ordererId,
                                 Integer totalPayment) {
        this.orderLineItems = orderLineItems;
        this.address = address;
        this.ordererId = ordererId;
        this.totalPayment = totalPayment;
    }

    public Address toAddress(){
        return new Address(address.getMajor(), address.getDetail(), address.getZipcode());
    }
}

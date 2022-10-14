package wayc.backend.order.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequestDto {

    private Long shopId;

    private List<CreateOrderLineItemRequestDto> orderLineItemsDto = new ArrayList<>();

    public CreateOrderRequestDto(Long shopId, List<CreateOrderLineItemRequestDto> orderLineItemsDto) {
        this.shopId = shopId;
        this.orderLineItemsDto = orderLineItemsDto;
    }
}


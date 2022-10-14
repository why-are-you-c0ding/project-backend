package wayc.backend.order.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.application.dto.request.CreateOrderRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PostOrderRequestDto {

    @NotNull
    private Long shopId;

    @Valid
    @NotEmpty
    private List<PostOrderLineItemRequestDto> orderLineItems = new ArrayList<>();

    public PostOrderRequestDto(Long shopId, List<PostOrderLineItemRequestDto> orderLineItemsDto) {
        this.shopId = shopId;
        this.orderLineItems = orderLineItemsDto;
    }

    public CreateOrderRequestDto toServiceDto(){
        return new CreateOrderRequestDto(
                shopId,
                orderLineItems
                        .stream()
                        .map(orderLineItemDto -> orderLineItemDto.toServiceDto())
                        .collect(Collectors.toList())
        );
    }
}


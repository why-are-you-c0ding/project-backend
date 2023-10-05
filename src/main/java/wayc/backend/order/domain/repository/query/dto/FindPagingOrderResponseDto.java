package wayc.backend.order.domain.repository.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindPagingOrderResponseDto <T> {

    private boolean finalPage;
    private List<T> orderLineItems;

    public FindPagingOrderResponseDto(boolean finalPage, List<T> orderLineItems) {
        this.finalPage = finalPage;
        this.orderLineItems = orderLineItems;
    }
}

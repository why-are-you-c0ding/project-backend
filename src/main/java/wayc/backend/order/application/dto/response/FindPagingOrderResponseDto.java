package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FindPagingOrderResponseDto<T> {


    private boolean finalPage;
    private T orders;

    public FindPagingOrderResponseDto(boolean finalPage, T orders) {
        this.finalPage = finalPage;
        this.orders = orders;
    }
}

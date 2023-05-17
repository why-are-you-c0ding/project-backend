package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class FindPagingOrderResponseDto <T> {


    private boolean finalPage;
    private List<T> orders;

    public FindPagingOrderResponseDto(boolean finalPage, List<T> orders) {
        this.finalPage = finalPage;
        this.orders = orders;
    }
}

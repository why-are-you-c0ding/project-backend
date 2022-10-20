package wayc.backend.order.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Order;
import wayc.backend.shop.domain.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ShowTotalOrderResponseDto<T> {


    private boolean finalPage;
    private T orders;

    public ShowTotalOrderResponseDto(boolean finalPage, T orders) {
        this.finalPage = finalPage;
        this.orders = orders;
    }
}

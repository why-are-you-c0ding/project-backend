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
public class ShowTotalOrderResponseDto {


    private boolean finalPage;
    private List<ShowOrdersResponseDto> orders = new ArrayList<>();

    public ShowTotalOrderResponseDto(boolean finalPage, List<ShowOrdersResponseDto> orders) {
        this.finalPage = finalPage;
        this.orders = orders;
    }
}

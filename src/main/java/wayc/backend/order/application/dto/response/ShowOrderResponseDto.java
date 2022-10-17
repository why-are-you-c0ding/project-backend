package wayc.backend.order.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.order.domain.Order;

@NoArgsConstructor
@Getter
public class ShowOrderResponseDto {

    public static ShowOrderResponseDto of(Order order) {
        return null;
    }
}

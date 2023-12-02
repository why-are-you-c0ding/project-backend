package wayc.backend.payment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefundPaymentRequest {
    private Long orderLineItemId;
    private Integer cancellationAmount;
}

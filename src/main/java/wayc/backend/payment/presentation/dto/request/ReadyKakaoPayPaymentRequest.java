package wayc.backend.payment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadyKakaoPayPaymentRequest {
    private Long orderId;
    private Integer payment;
}

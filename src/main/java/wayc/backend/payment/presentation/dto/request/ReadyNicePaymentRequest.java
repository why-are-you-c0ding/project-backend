package wayc.backend.payment.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ReadyNicePaymentRequest {
    private String authResultCode;
    private String authResultMsg;
    private String tid;
    private String clientId;
    private String orderId;
    private String amount;
    private String mallReserved;
    private String authToken;
    private String signature;
}

package wayc.backend.payment.application.dto.request;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ApproveNicePaymentRequestDto {
    private final String authResultCode;
    private final String authResultMsg;
    private final String tid;
    private final String clientId;
    private final String orderId;
    private final String amount;
    private final String mallReserved;
    private final String authToken;
    private final String signature;

    public ApproveNicePaymentRequestDto(String authResultCode,
                                        String authResultMsg,
                                        String tid,
                                        String clientId,
                                        String orderId,
                                        String amount,
                                        String mallReserved,
                                        String authToken,
                                        String signature) {
        this.authResultCode = authResultCode;
        this.authResultMsg = authResultMsg;
        this.tid = tid;
        this.clientId = clientId;
        this.orderId = orderId;
        this.amount = amount;
        this.mallReserved = mallReserved;
        this.authToken = authToken;
        this.signature = signature;
    }
}

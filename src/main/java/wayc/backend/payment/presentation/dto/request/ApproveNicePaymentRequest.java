package wayc.backend.payment.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wayc.backend.payment.application.dto.request.ApproveNicePaymentRequestDto;

@ToString
@Getter @Setter
public class ApproveNicePaymentRequest {

    private String authResultCode;
    private String authResultMsg;
    private String tid;
    private String clientId;
    private String orderId;
    private String amount;
    private String mallReserved;
    private String authToken;
    private String signature;

    public ApproveNicePaymentRequestDto toServiceDto() {
        return new ApproveNicePaymentRequestDto(
                authResultCode,
                authResultMsg,
                tid,
                clientId,
                orderId,
                amount,
                mallReserved,
                authToken,
                signature
        );
    }
}

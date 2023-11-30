package wayc.backend.payment.domain.nicepayment.approve;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicePaymentApproveApiRequest {

    private Integer amount;

    public NicePaymentApproveApiRequest(Integer amount) {
        this.amount = amount;
    }
}

package wayc.backend.payment.domain.nicepayment.approve;

public interface NicePaymentApproveApiService {
    NicePaymentApproveApiResponse approve(String tid, Integer amount);
}

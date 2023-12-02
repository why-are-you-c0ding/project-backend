package wayc.backend.payment.domain.kakaopay.refund;

public interface KakaoPayRefundApiService {
    KakaoPayCancelApiResponse refund(String tid, Integer cancelPayment);
}

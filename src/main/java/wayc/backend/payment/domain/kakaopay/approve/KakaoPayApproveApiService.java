package wayc.backend.payment.domain.kakaopay.approve;

import wayc.backend.payment.domain.Payment;

public interface KakaoPayApproveApiService {
    KakaoPayApproveApiResponse approve(String pgToken, Payment payment, Long userId);
}

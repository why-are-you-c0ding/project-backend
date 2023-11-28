package wayc.backend.payment.domain.kakaopay.find;

import wayc.backend.payment.domain.Payment;

public interface KakaoPayFindApiService {
    KakaoPayFindApiResponse find(Payment payment);
}

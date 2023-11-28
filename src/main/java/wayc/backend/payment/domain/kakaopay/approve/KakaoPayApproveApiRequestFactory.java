package wayc.backend.payment.domain.kakaopay.approve;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.domain.kakaopay.approve.KakaoPayApproveApiRequest;
import wayc.backend.payment.domain.kakaopay.KakaoPayProperties;

@Component
@RequiredArgsConstructor
public class KakaoPayApproveApiRequestFactory {

    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayApproveApiRequest getKakaoPayApproveApiRequest(String pgToken,
                                                                  Payment payment,
                                                                  Long userId) {
        return KakaoPayApproveApiRequest
                .Builder()
                .setAdminKey(kakaoPayProperties.getAdminKey())
                .setCid(kakaoPayProperties.getCid())
                .setCidSecret(kakaoPayProperties.getCidSecret())
                .setTid(payment.getPlatformTransactionId())
                .setPartnerOrderId(String.valueOf(payment.getOrderId()))
                .setPartnerUserId(String.valueOf(userId))
                .setPgToken(pgToken)
                .setPayload(null)
                .setTotalAmount(payment.getPay())
                .build();
    }
}

package wayc.backend.payment.domain.kakaopay.refund;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayCancelApiRequest;
import wayc.backend.payment.domain.kakaopay.KakaoPayProperties;

@Component
@RequiredArgsConstructor
public class KakaoPayCancelApiRequestFactory {

    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayCancelApiRequest getKakaoPayCancelApiRequest(String tid,
                                                                Integer cancelAmount) {
        return KakaoPayCancelApiRequest
                .builder()
                .setCid(kakaoPayProperties.getCid())
                .setCidSecret(kakaoPayProperties.getCidSecret())
                .setTid(tid)
                .setCancelAmount(cancelAmount)
                .setCancelTaxFreeAmount(0)
                //.setCancelVatAmount() 취소 부가세 금액
                //.setCancelAvailableAmount() 취소 가능 금액
                //.setPayload()
                .build();
    }
}

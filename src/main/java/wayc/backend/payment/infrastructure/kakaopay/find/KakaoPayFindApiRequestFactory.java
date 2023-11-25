package wayc.backend.payment.infrastructure.kakaopay.find;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.payment.infrastructure.kakaopay.KakaoPayProperties;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoPayFindApiRequestFactory {

    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayFindApiRequest getKakaoPayFindApiRequest(String tid) {
        return KakaoPayFindApiRequest
                .builder()
                .setAdminKey(kakaoPayProperties.getAdminKey())
                .setCid(kakaoPayProperties.getCid())
                .setCidSecret(kakaoPayProperties.getCidSecret())
                .setTid(tid)
                .build();
    }

}

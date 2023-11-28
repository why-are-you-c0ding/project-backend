package wayc.backend.payment.infrastructure.kakaopay.find;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.payment.domain.kakaopay.find.KakaoPayFindApiRequest;
import wayc.backend.payment.infrastructure.kakaopay.KakaoPayProperties;

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

package wayc.backend.payment.infrastructure.kakaopay.find;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import wayc.backend.payment.domain.kakaopay.find.KakaoPayFindApiRequest;
import wayc.backend.payment.infrastructure.kakaopay.AbstractKakaoPayRequestHttpEntityFactory;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiRequest;

@Component
@RequiredArgsConstructor
public class KakaoPayFindApiRequestHttpEntityFactory extends AbstractKakaoPayRequestHttpEntityFactory {

    public HttpEntity<KakaoPayReadyApiRequest> createFindApiRequestHttpEntity(KakaoPayFindApiRequest request) {
        HttpHeaders headers = makerHeader(request.getAdminKey());
        MultiValueMap<String, String> body = makeBody(request);
        return new HttpEntity(body, headers);
    }

    private MultiValueMap<String, String> makeBody(KakaoPayFindApiRequest request) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", request.getCid());
        body.add("tid", request.getTid());
        //body.add("cid_secret", "가맹점 코드 인증 키, 24자, 숫자와 영문 소문자 조합")); 필수 X
        return body;
    }
}

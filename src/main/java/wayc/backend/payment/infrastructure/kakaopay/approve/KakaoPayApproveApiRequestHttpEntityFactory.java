package wayc.backend.payment.infrastructure.kakaopay.approve;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import wayc.backend.payment.infrastructure.kakaopay.AbstractKakaoPayRequestHttpEntityFactory;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;

@Component
@RequiredArgsConstructor
public class KakaoPayApproveApiRequestHttpEntityFactory extends AbstractKakaoPayRequestHttpEntityFactory {

    public HttpEntity<KakaoPayReadyApiRequest> createReadyApiRequestHttpEntity(KakaoPayApproveApiRequest request) {
        HttpHeaders headers = makerHeader(request.getAdminKey());
        MultiValueMap<String, String> body = makeBody(request);
        return new HttpEntity(body, headers);
    }

    private MultiValueMap<String, String> makeBody(KakaoPayApproveApiRequest request) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", request.getCid());
        body.add("tid", request.getTid());
        body.add("partner_order_id", request.getPartnerOrderId());
        body.add("partner_user_id", request.getPartnerUserId());
        body.add("pg_token", request.getPgToken());
        body.add("total_amount", String.valueOf(request.getTotalAmount()));
        //body.add("cid_secret", "가맹점 코드 인증키"); 필수 X
        //body.add("payload", "결제 승인 요청에 대해 저장하고 싶은 값"); 필수 X
        return body;
    }
}

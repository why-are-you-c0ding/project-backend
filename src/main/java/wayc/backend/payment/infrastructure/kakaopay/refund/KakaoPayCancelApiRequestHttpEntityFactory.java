package wayc.backend.payment.infrastructure.kakaopay.refund;

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
public class KakaoPayCancelApiRequestHttpEntityFactory extends AbstractKakaoPayRequestHttpEntityFactory {

    public HttpEntity<KakaoPayReadyApiRequest> createCancelApiRequestHttpEntity(KakaoPayCancelApiRequest request) {
        HttpHeaders headers = makerHeader(request.getAdminKey());
        MultiValueMap<String, String> body = makeBody(request);
        return new HttpEntity(body, headers);
    }

    private MultiValueMap<String, String> makeBody(KakaoPayCancelApiRequest request) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", request.getCid());
        //body.add("cid_secret", request.getCidSecret()); 필수 X
        body.add("tid", request.getTid());
        body.add("cancel_amount", String.valueOf(request.getCancelAmount()));
        body.add("cancel_tax_free_amount", String.valueOf(request.getCancelTaxFreeAmount()));
        body.add("cancel_vat_amount", String.valueOf(request.getCancelVatAmount())); //필수 X
        body.add("cancel_available_amount", String.valueOf(request.getCancelAvailableAmount())); //필수 X
        body.add("payload", request.getPayload()); //필수 X
        return body;
    }
}

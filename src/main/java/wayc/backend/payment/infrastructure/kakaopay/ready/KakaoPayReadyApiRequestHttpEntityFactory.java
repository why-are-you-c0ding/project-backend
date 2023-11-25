package wayc.backend.payment.infrastructure.kakaopay;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class KakaoPayRequestHttpEntityFactory {


    public HttpEntity<KakaoPayReadyApiRequest> createReadyApiRequestHttpEntity(KakaoPayReadyApiRequest request) {
        HttpHeaders headers = makerHeader(request);
        MultiValueMap<String, String> body = makeBody(request);
        return new HttpEntity(body, headers);
    }

    private MultiValueMap<String, String> makeBody(KakaoPayReadyApiRequest request) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", request.getCid());
        body.add("partner_order_id", request.getPartnerOrderId());
        body.add("partner_user_id", request.getPartnerUserId());
        body.add("item_name", request.getItemName());
        body.add("quantity", String.valueOf(request.getQuantity()));
        body.add("total_amount", String.valueOf(request.getTotalAmount()));
        body.add("tax_free_amount", String.valueOf(request.getTaxFreeAmount()));
        body.add("approval_url", request.getApprovalUrl());
        body.add("cancel_url", request.getCancelUrl());
        body.add("fail_url", request.getFailUrl());
        //body.add("vat_amount", "부가세"); 필수 X
        //body.add("green_deposit", "캅 보증금"); 필수 X
        //body.add("item_code", "상품코드"); 필수 X
        //body.add("cid_secret", "가맹점 코드 인증 키, 24자, 숫자와 영문 소문자 조합")); 필수 X
        //body.add("custom_json", "결제 화면에 보여줄 사용자 정의 문구"); 필수 X
        //body.add("install_month", "카드 할부 개월, 0 ~ 12"); 필수 X
        //body.add("payment_method_type", "사용 허가할 결제 수단, 지정하지 않으면 모든 결제 수단 허용"); 필수 X
        //body.add("available_cars", "결제 수단으로써 사용 허가할 카드사를 지정해야 하는 경우 사용,"); 필수 X
        return body;
    }

    private HttpHeaders makerHeader(KakaoPayReadyApiRequest request) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        headers.set("Authorization", "KakaoAK " + request.getAdminKey());
        return headers;
    }
}

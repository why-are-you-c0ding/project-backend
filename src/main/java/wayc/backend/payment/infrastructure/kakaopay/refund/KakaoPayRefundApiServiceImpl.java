package wayc.backend.payment.infrastructure.kakaopay.refund;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiService;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayCancelApiRequest;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayCancelApiRequestFactory;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayCancelApiResponse;
import wayc.backend.payment.domain.kakaopay.KakaoPayProperties;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiRequest;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayRefundApiService;

@Component
@RequiredArgsConstructor
public class KakaoPayRefundApiServiceImpl implements KakaoPayRefundApiService {

    private final RestTemplate restTemplate;
    private final KakaoPayCancelApiRequestFactory kakaoPayCancelApiRequestFactory;
    private final KakaoPayCancelApiRequestHttpEntityFactory kakaoPayCancelApiRequestHttpEntityFactory;
    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayCancelApiResponse refund(String tid,
                                            Integer cancelPayment) {
        KakaoPayCancelApiRequest request = kakaoPayCancelApiRequestFactory.getKakaoPayCancelApiRequest(tid, cancelPayment);
        HttpEntity<KakaoPayReadyApiRequest> httpEntity = kakaoPayCancelApiRequestHttpEntityFactory.createCancelApiRequestHttpEntity(request);
        return restTemplate
                .postForEntity(
                        kakaoPayProperties.getCancelApi().getApiUrl(),
                        httpEntity,
                        KakaoPayCancelApiResponse.class
                )
                .getBody();
    }
}

package wayc.backend.payment.infrastructure.kakaopay.find;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.infrastructure.kakaopay.KakaoPayProperties;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiRequest;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiRequestFactory;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiRequestHttpEntityFactory;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiResponse;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;

@Component
@RequiredArgsConstructor
public class KakaoPayFindApiService {

    private final RestTemplate restTemplate;
    private final KakaoPayFindApiRequestFactory kakaoPayFindApiRequestFactory;
    private final KakaoPayFindApiRequestHttpEntityFactory kakaoPayFindApiRequestHttpEntityFactory;
    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayFindApiResponse find(Payment payment) {
        KakaoPayFindApiRequest request = kakaoPayFindApiRequestFactory.getKakaoPayFindApiRequest(payment.getPlatformTransactionId());
        HttpEntity<KakaoPayReadyApiRequest> httpEntity = kakaoPayFindApiRequestHttpEntityFactory.createFindApiRequestHttpEntity(request);
        return requestAndGetResponse(httpEntity);
    }

    private KakaoPayFindApiResponse requestAndGetResponse(HttpEntity<KakaoPayReadyApiRequest> httpEntity) {
        return restTemplate
                .postForEntity(
                        kakaoPayProperties.getFindApi().getApiUrl(),
                        httpEntity,
                        KakaoPayFindApiResponse.class
                )
                .getBody();
    }
}

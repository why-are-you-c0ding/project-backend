package wayc.backend.payment.infrastructure.kakaopay.approve;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.infrastructure.kakaopay.KakaoPayProperties;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;

@Component
@RequiredArgsConstructor
public class KakaoPayApproveApiService {

    private final RestTemplate restTemplate;
    private final KakaoPayApproveApiRequestHttpEntityFactory kakaoPayApproveApiRequestHttpEntityFactory;
    private final KakaoPayApproveApiRequestFactory kakaoPayApproveApiRequestFactory;
    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayApproveApiResponse approve(String pgToken, Payment payment, Long userId) {
        KakaoPayApproveApiRequest request = kakaoPayApproveApiRequestFactory.getKakaoPayApproveApiRequest(pgToken, payment, userId);
        HttpEntity<KakaoPayReadyApiRequest> httpEntity = kakaoPayApproveApiRequestHttpEntityFactory.createReadyApiRequestHttpEntity(request);
        return requestAndGetResponse(httpEntity);
    }

    private KakaoPayApproveApiResponse requestAndGetResponse(HttpEntity<KakaoPayReadyApiRequest> httpEntity) {
        return restTemplate
                .postForEntity(
                        kakaoPayProperties.getApproveApi().getApiUrl(),
                        httpEntity,
                        KakaoPayApproveApiResponse.class
                )
                .getBody();
    }
}

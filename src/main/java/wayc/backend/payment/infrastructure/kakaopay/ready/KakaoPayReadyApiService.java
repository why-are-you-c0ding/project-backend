package wayc.backend.payment.infrastructure.kakaopay.ready;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wayc.backend.order.domain.Order;
import wayc.backend.payment.infrastructure.kakaopay.KakaoPayProperties;

@Component
@RequiredArgsConstructor
public class KakaoPayReadyApiService {

    private final RestTemplate restTemplate;
    private final KakaoPayReadyApiRequestFactory kakaoPayReadyApiRequestFactory;
    private final KakaoPayReadyApiRequestHttpEntityFactory kakaoPayRequestHttpEntityFactory;
    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayReadyApiResponse ready(Order order) {
        KakaoPayReadyApiRequest request = kakaoPayReadyApiRequestFactory.getKakaoPayReadyApiRequest(order);
        HttpEntity<KakaoPayReadyApiRequest> httpEntity = kakaoPayRequestHttpEntityFactory.createReadyApiRequestHttpEntity(request);
        return requestAndGetResponse(httpEntity);
    }

    private KakaoPayReadyApiResponse requestAndGetResponse(HttpEntity<KakaoPayReadyApiRequest> httpEntity) {
        return restTemplate
                .postForEntity(
                        kakaoPayProperties.getReadyApi().getApiUrl(),
                        httpEntity,
                        KakaoPayReadyApiResponse.class
                )
                .getBody();
    }

}

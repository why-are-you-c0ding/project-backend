package wayc.backend.payment.infrastructure.nicepayment;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import wayc.backend.payment.domain.nicepayment.NicePaymentProperties;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiRequest;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiResponse;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiService;

@Component
@RequiredArgsConstructor
public class NicePaymentApproveApiServiceImpl implements NicePaymentApproveApiService {

    private final RestTemplate restTemplate;
    private final NicePaymentProperties nicePaymentProperties;
    private final NicePaymentApproveApiRequestHttpEntityFactory nicePaymentApproveApiRequestHttpEntityFactory;

    @Override
    public NicePaymentApproveApiResponse approve(String tid, Integer amount) {
        HttpEntity<NicePaymentApproveApiRequest> httpEntity = nicePaymentApproveApiRequestHttpEntityFactory.createRequestHttpEntity(amount);
        return requestAndGetResponse(amount, tid, httpEntity);
    }
    private NicePaymentApproveApiResponse requestAndGetResponse(Integer amount,
                                                                String tid,
                                                                HttpEntity<NicePaymentApproveApiRequest> httpEntity) {
        return restTemplate
                .exchange(
                        nicePaymentProperties.getApproveUrl() + "/" + tid,
                        HttpMethod.POST,
                        httpEntity,
                        NicePaymentApproveApiResponse.class
                )
                .getBody();
    }
}

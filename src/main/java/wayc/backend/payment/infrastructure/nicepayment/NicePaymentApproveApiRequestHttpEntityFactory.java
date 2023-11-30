package wayc.backend.payment.infrastructure.nicepayment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import wayc.backend.payment.domain.nicepayment.NicePaymentProperties;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiRequest;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class NicePaymentApproveApiRequestHttpEntityFactory {

    private final NicePaymentProperties nicePaymentProperties;

    public HttpEntity<NicePaymentApproveApiRequest> createRequestHttpEntity(Integer amount) {
        HttpHeaders headers = makerHeader();
        NicePaymentApproveApiRequest body = makeBody(amount);
        return new HttpEntity(body, headers);
    }

    private HttpHeaders makerHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String authKey = nicePaymentProperties.getClientKey() + ":" + nicePaymentProperties.getSecretKey();
        String encode = Base64.getEncoder().encodeToString(authKey.getBytes());
        headers.set("Authorization", "Basic " + encode);
        return headers;
    }

    private NicePaymentApproveApiRequest makeBody(Integer amount) {
        return new NicePaymentApproveApiRequest(amount);
    }
}

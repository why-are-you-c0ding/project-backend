package wayc.backend.payment.infrastructure.kakaopay;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;


public abstract class AbstractKakaoPayRequestHttpEntityFactory {

    protected HttpHeaders makerHeader(String adminKey) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);
        headers.set("Authorization", "KakaoAK " + adminKey);
        return headers;
    }
}

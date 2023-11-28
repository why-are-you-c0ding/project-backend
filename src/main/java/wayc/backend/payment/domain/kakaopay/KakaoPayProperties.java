package wayc.backend.payment.infrastructure.kakaopay;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kakao.pay")
public class KakaoPayProperties {

    private String adminKey;
    private String cid;
    private String cidSecret;
    private ReadyApi readyApi;
    private ApproveApi approveApi;
    private FindApi findApi;
    private CancelApi cancelApi;

    @Setter
    @Getter
    public static class ReadyApi {
        private String apiUrl;
        private String approvalUrl;
        private String cancelUrl;
        private String failUrl;
    }

    @Getter
    @Setter
    public static class ApproveApi {
        private String apiUrl;
    }

    @Getter
    @Setter
    public static class FindApi {
        private String apiUrl;
    }

    @Getter
    @Setter
    public static class CancelApi {
        private String apiUrl;
    }
}

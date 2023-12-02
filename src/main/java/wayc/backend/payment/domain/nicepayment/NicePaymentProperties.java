package wayc.backend.payment.domain.nicepayment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "nicepayment")
public class NicePaymentProperties {

    private String approveUrl;
    private String secretKey;
    private String clientKey;
}

package wayc.backend.payment.application;

public interface KakaoPayService {
    void approve(String pgToken, Long orderId);
}


package wayc.backend.payment.domain.kakaopay.ready;

import wayc.backend.order.domain.Order;

public interface KakaoPayReadyApiService {
    KakaoPayReadyApiResponse ready(Order order);
}

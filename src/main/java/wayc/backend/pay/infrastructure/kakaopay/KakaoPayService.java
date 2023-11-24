package wayc.backend.pay.infrastructure.kakaopay;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.pay.domain.PayService;
import wayc.backend.pay.infrastructure.kakaopay.approve.KakaoPayApproveApiResponse;
import wayc.backend.pay.infrastructure.kakaopay.approve.KakaoPayApproveApiService;
import wayc.backend.pay.infrastructure.kakaopay.ready.KakaoPayReadyApiRequest;
import wayc.backend.pay.infrastructure.kakaopay.ready.KakaoPayReadyApiRequestFactory;
import wayc.backend.pay.infrastructure.kakaopay.ready.KakaoPayReadyApiResponse;
import wayc.backend.pay.infrastructure.kakaopay.ready.KakaoPayReadyApiService;

@Component
@RequiredArgsConstructor
public class KakaoPayService implements PayService {


    private final OrderRepository orderRepository;
    private final KakaoPayReadyApiService kakaoPayReadyApiService;
    private final KakaoPayApproveApiService kakaoPayApproveApiService;

    @Override
    public void pay(Long orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);
        KakaoPayReadyApiResponse readyApiResponse = kakaoPayReadyApiService.ready(order);
        System.out.println("readyApiResponse = " + readyApiResponse);
    }
}
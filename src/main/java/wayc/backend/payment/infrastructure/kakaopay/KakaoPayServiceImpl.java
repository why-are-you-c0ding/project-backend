package wayc.backend.payment.infrastructure.kakaopay;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.payment.application.KakaoPayService;
import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.domain.PaymentRepository;
import wayc.backend.payment.domain.PaymentService;
import wayc.backend.payment.exception.NotExistsPaymentException;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiResponse;
import wayc.backend.payment.infrastructure.kakaopay.approve.KakaoPayApproveApiService;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiResponse;
import wayc.backend.payment.infrastructure.kakaopay.ready.KakaoPayReadyApiService;

@Component
@RequiredArgsConstructor
public class KakaoPayServiceImpl implements PaymentService, KakaoPayService {

    private final OrderRepository orderRepository;
    private final PaymentRepository payRepository;
    private final KakaoPayReadyApiService kakaoPayReadyApiService;
    private final KakaoPayApproveApiService kakaoPayApproveApiService;

    @Override
    public void pay(Long orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);
        KakaoPayReadyApiResponse readyApiResponse = kakaoPayReadyApiService.ready(order);
        System.out.println("readyApiResponse = " + readyApiResponse);
        payRepository.save(new Payment(order.getTotalPayment(), order.getId(), readyApiResponse.getTid()));
    }

    @Override
    public void approve(String pgToken, Long orderId) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);
        Payment payment = payRepository.findByOrderId(orderId).orElseThrow(NotExistsPaymentException::new);
        KakaoPayApproveApiResponse approveApiResponse = kakaoPayApproveApiService.approve(pgToken, payment, order.getId());
        System.out.println(approveApiResponse);
    }
}
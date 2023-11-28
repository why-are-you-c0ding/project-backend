package wayc.backend.payment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.exception.NotExistsOrderException;

import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.domain.PaymentRepository;
import wayc.backend.payment.domain.kakaopay.approve.KakaoPayApproveApiResponse;
import wayc.backend.payment.domain.kakaopay.approve.KakaoPayApproveApiService;
import wayc.backend.payment.domain.kakaopay.find.KakaoPayFindApiResponse;
import wayc.backend.payment.domain.kakaopay.find.KakaoPayFindApiService;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiService;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayCancelApiResponse;
import wayc.backend.payment.domain.kakaopay.refund.KakaoPayRefundApiService;
import wayc.backend.payment.exception.NotExistsPaymentException;
import wayc.backend.payment.exception.PaymentCancelFailureException;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiResponse;


@Component
@RequiredArgsConstructor
public class KakaoPayService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final PaymentRepository payRepository;

    private final KakaoPayReadyApiService kakaoPayReadyApiService;
    private final KakaoPayApproveApiService kakaoPayApproveApiService;
    private final KakaoPayFindApiService kakaoPayFindApiService;
    private final KakaoPayRefundApiService kakaoPayRefundApiService;

    //TODO kakaoAPI에 취소한 주문상품 id 저장해야함.

    @Transactional
    public String ready(Long orderId, Integer payment) {
        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);

        //TODO order와 payment가 동일한지 검증해야함.

        KakaoPayReadyApiResponse readyApiResponse = kakaoPayReadyApiService.ready(order);
        payRepository.save(new Payment(order.getTotalPayment(), order.getId(), readyApiResponse.getTid()));
        order.readyPayment();
        return readyApiResponse.getNextRedirectPcUrl();
    }

    @Transactional
    public void approve(String pgToken, Long orderId) {

        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);

        //TODO 재고 검사를 해야하고 제고를 빼야함.

        Payment payment = payRepository.findByOrderId(orderId).orElseThrow(NotExistsPaymentException::new);
        KakaoPayApproveApiResponse approveApiResponse = kakaoPayApproveApiService.approve(pgToken, payment, order.getId());
        order.completePayment();
    }

    @Transactional
    public void cancel(Long orderId) {
        Payment payment = payRepository.findByOrderId(orderId).orElseThrow(NotExistsPaymentException::new);
        KakaoPayFindApiResponse response = kakaoPayFindApiService.find(payment);

        /**
         * 정상적으로 취소가 됐는지 검사.
         */
        if(!response.getStatus().equals("QUIT_PAYMENT")) {
            //여긴 무슨 로직이 들어가야할까
            throw new PaymentCancelFailureException();
        }

        Order order = orderRepository.findOrderById(orderId).orElseThrow(NotExistsOrderException::new);
        payment.delete(order);
    }

    public void refund(Long orderLineItemId, Integer cancellationAmount) {
        OrderLineItem orderLineItem = orderLineItemRepository.findByIdAndStatus(orderLineItemId).orElseThrow(NotExistsOrderException::new);

        //TODO 취소 금액과 주문 상품의 금액이 같은지 비교.

        Payment payment = payRepository.findByOrderId(orderLineItemId).orElseThrow(NotExistsPaymentException::new);

        //TODO 결제 감소
        payment.refund(orderLineItem);

        //TODO 재고 복귀시켜야함
        KakaoPayCancelApiResponse response = kakaoPayRefundApiService.refund(payment.getPlatformTransactionId(), orderLineItem.getPayment());
    }
}
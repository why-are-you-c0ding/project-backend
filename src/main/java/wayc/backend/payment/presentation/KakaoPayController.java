package wayc.backend.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wayc.backend.common.CommandSuccessResponse;
import wayc.backend.payment.application.KakaoPayService;
import wayc.backend.payment.exception.PaymentFailedException;
import wayc.backend.payment.presentation.dto.request.ReadyKakaoPayPaymentRequest;
import wayc.backend.payment.presentation.dto.request.RefundPaymentRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/payment/kakaopay")
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final RedirectStrategy redirectStrategy;

    @PostMapping
    public void ready(@Validated @RequestBody ReadyKakaoPayPaymentRequest request,
                      HttpServletRequest servletRequest,
                      HttpServletResponse servletResponse) throws IOException {
        String successRedirectUrl = kakaoPayService.ready(request.getOrderId(), request.getPayment());
        redirectStrategy.sendRedirect(servletRequest, servletResponse, successRedirectUrl);
    }

    /**
     * 사용자가 결제를 취소한 경우, 보안을 위해 주문 상세 조회 API를 호출하고 결제 과정을 중단해야 합니다.
     * 조회 시 상태 값이 사용자가 결제를 중단한 상태임을 나타내는 QUIT_PAYMENT인 것을 확인하고 결제 중단 처리를 해야 합니다.
     */
    @GetMapping("/cancel")
    public ResponseEntity<CommandSuccessResponse> cancel(@RequestParam("partner_order_id") Long partnerOrderId) {
        kakaoPayService.cancel(partnerOrderId);
        return ResponseEntity.ok(new CommandSuccessResponse("결제 취소를 성공하셨습니다."));
    }

    @GetMapping("/fail")
    public void fail() {
        throw new PaymentFailedException();
    }

    @GetMapping("/approve")
    public void approve(@RequestParam("pg_token") String pgToken, @RequestParam("partner_order_id") Long partnerOrderId) {
        kakaoPayService.approve(pgToken, partnerOrderId);
    }

    @PostMapping("/refund")
    public ResponseEntity<CommandSuccessResponse> refund(@Validated @RequestBody RefundPaymentRequest request) {
        kakaoPayService.refund(request.getOrderLineItemId(), request.getCancellationAmount());
        return ResponseEntity.ok(new CommandSuccessResponse("환불을 요청을 성공했습니다."));
    }
}

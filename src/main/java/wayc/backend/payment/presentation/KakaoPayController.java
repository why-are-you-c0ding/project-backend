package wayc.backend.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wayc.backend.common.CommandSuccessResponse;
import wayc.backend.payment.application.KakaoPayService;
import wayc.backend.payment.application.dto.response.CreatePayResponseDto;
import wayc.backend.payment.domain.PaymentService;
import wayc.backend.payment.exception.PaymentFailedException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/payment/kakaopay")
public class KakaoPayController {

    private final PaymentService payService;
    private final KakaoPayService kakaoPayService;


    @PostMapping
    public ResponseEntity<CreatePayResponseDto> postPay(@RequestParam Long orderId){
        payService.pay(orderId);
        return ResponseEntity.ok(new CreatePayResponseDto());
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
}

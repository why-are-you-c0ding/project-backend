package wayc.backend.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/cancel")
    public void cancel() {
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

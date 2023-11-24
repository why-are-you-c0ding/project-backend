package wayc.backend.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wayc.backend.payment.application.dto.response.CreatePayResponseDto;
import wayc.backend.payment.domain.PaymentService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService payService;

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

    }

    @GetMapping("/approve")
    public void approve() {

    }
}

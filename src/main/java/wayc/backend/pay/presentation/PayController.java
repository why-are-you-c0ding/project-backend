package wayc.backend.pay.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wayc.backend.pay.application.dto.response.CreatePayResponseDto;
import wayc.backend.pay.domain.PayService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PayController {

    private final PayService payService;

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

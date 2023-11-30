package wayc.backend.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wayc.backend.common.CommandSuccessResponse;
import wayc.backend.payment.application.NicePaymentService;
import wayc.backend.payment.presentation.dto.request.ApproveNicePaymentRequest;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment/nice-payments")
public class NicePaymentController {

    private final NicePaymentService nicePaymentService;

    @PostMapping("/approve")
    public CommandSuccessResponse ready(@Validated @ModelAttribute ApproveNicePaymentRequest request) throws IOException {
        nicePaymentService.approve(request.toServiceDto());
        //TODO 주문 완료 화면으로 리다이렉트해야함.
        return new CommandSuccessResponse("결제에 성공하였습니다");
    }
}

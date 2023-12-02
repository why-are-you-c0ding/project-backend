package wayc.backend.payment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.payment.application.dto.request.ApproveNicePaymentRequestDto;
import wayc.backend.payment.domain.Payment;
import wayc.backend.payment.domain.PaymentRepository;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiResponse;
import wayc.backend.payment.domain.nicepayment.approve.NicePaymentApproveApiService;

@Service
@RequiredArgsConstructor
public class NicePaymentService {

    private final PaymentRepository paymentRepository;
    private final NicePaymentApproveApiService nicePaymentApproveApiService;

    /**
     * 주문은 사전에 생성됨. 상품 옵션과 주문 옵션은 이미 검증된 상태
     */
    public void approve(ApproveNicePaymentRequestDto dto) {
        System.out.println("dto = " + dto);
        //TODO 데이터의 위변조를 체크, 즉 결제 금액을 체크해야할 듯
        Payment payment = new Payment(Integer.parseInt(dto.getAmount()), Long.valueOf(dto.getOrderId()), dto.getTid()); //임시 값
        paymentRepository.save(payment);

        //TODO 재고가 남아있는지 검사하고 이를 감소시켜야함.



        NicePaymentApproveApiResponse result = nicePaymentApproveApiService.approve(dto.getTid(), Integer.parseInt(dto.getAmount()));



    }
}

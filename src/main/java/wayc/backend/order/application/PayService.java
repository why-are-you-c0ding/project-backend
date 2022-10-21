package wayc.backend.order.application;

import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

import java.util.List;

public interface PayService {
    void createPay(Long memberId, List<CreatePayRequestDto> dtoList );
}

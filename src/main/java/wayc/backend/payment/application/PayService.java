package wayc.backend.payment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.payment.domain.PaymentRepository;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PaymentRepository payRepository;
    private final OrderLineItemRepository orderRepository;
}


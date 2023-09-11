package wayc.backend.pay.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import wayc.backend.order.domain.repository.OrderLineItemRepository;
import wayc.backend.order.domain.repository.PayRepository;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final OrderLineItemRepository orderRepository;
}


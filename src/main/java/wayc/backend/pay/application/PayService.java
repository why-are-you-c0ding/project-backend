package wayc.backend.pay.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wayc.backend.order.exception.NotExistsOrderException;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderStatus;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.domain.Pay;
import wayc.backend.order.domain.repository.PayRepository;

import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final OrderRepository orderRepository;
}


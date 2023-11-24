package wayc.backend.integration.pay;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;
import wayc.backend.payment.domain.PaymentService;

public class PayServiceIntegrationTest extends IntegrationTest {

    @Autowired
    PaymentService payService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void pay() {


        CreateOrderRequest request = CreateOrderRequestFactory.createSuccessCase();
        Order order = new OrderMapper().mapFrom(request.toServiceDto(1L));
        orderRepository.save(order);
        payService.pay(order.getId());

    }

}

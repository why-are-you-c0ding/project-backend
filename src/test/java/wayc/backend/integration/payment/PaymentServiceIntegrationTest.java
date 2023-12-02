package wayc.backend.integration.payment;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import wayc.backend.factory.order.CreateOrderRequestFactory;
import wayc.backend.integration.IntegrationTest;
import wayc.backend.order.application.OrderMapper;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.repository.OrderRepository;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;

public class PaymentServiceIntegrationTest extends IntegrationTest {

}

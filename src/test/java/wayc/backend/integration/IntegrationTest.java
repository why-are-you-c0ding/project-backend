package wayc.backend.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import wayc.backend.member.application.EmailService;
import wayc.backend.member.domain.service.SendEmailService;

@SpringBootTest
@Transactional
public class IntegrationTest {

    @MockBean
    SendEmailService sendEmailService;
}

package wayc.backend.member.infrastructure;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;

import com.amazonaws.services.simpleemail.model.SendEmailResult;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.exception.email.FailSendEmailException;

import java.util.List;

import static wayc.backend.member.infrastructure.AwsSesUtils.*;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    @Value("${aws.ses.sender}")
    private String sender;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void sendVerificationEmail(String receiveEmail, String authKey) {
        EmailSenderDto emailSenderDto = makeEmailSenderDto(receiveEmail, authKey);
        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailSenderDto.toSendRequestDto());
        confirmSentEmail(sendEmailResult);
    }

    private EmailSenderDto makeEmailSenderDto(String receiveEmail, String authKey) {
        return EmailSenderDto.of(sender, List.of(receiveEmail), getSubject(), getEmailVerificationHtml(authKey));
    }

    private void confirmSentEmail(SendEmailResult sendEmailResult) {
        if (sendEmailResult.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            throw new FailSendEmailException();
        }
    }
}

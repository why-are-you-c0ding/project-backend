package wayc.backend.member.infrastructure;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;

import com.amazonaws.services.simpleemail.model.SendEmailResult;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import wayc.backend.member.domain.service.SendEmailService;
import wayc.backend.member.exception.email.FailSendEmailException;
import wayc.backend.member.presentation.dto.response.ValidateEmailResponse;

import java.util.List;

import static wayc.backend.utils.AwsSesUtils.*;

@Service
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService {

    @Value("${aws.ses.sender}")
    private String sender;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public ValidateEmailResponse sendVerificationEmail(String receiveEmail) {
        String authKey = createAuthKey();
        EmailSenderDto emailSenderDto = makeEmailSenderDto(receiveEmail, authKey);
        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailSenderDto.toSendRequestDto());
        confirmSentEmail(sendEmailResult);
        return new ValidateEmailResponse(authKey, receiveEmail);
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

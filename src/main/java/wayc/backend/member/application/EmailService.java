package wayc.backend.member.application;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;

import com.amazonaws.services.simpleemail.model.SendEmailResult;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import wayc.backend.member.exception.FailSendEmailException;
import wayc.backend.member.infrastructure.EmailSenderDto;
import wayc.backend.member.presentation.dto.response.VerificationEmailInfoDto;

import java.util.Arrays;
import java.util.List;

import static wayc.backend.utils.AwsSesUtils.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${aws.ses.sender}")
    private String sender;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public VerificationEmailInfoDto sendVerificationEmail(String receiveEmail) {
        String authKey = createAuthKey();
        EmailSenderDto emailSenderDto = makeEmailSenderDto(receiveEmail, authKey);
        SendEmailResult sendEmailResult = amazonSimpleEmailService.sendEmail(emailSenderDto.toSendRequestDto());
        confirmSentEmail(sendEmailResult);
        return new VerificationEmailInfoDto(authKey, receiveEmail);
    }

    private EmailSenderDto makeEmailSenderDto(String receiveEmail, String authKey) {
        List<String> receiver = Arrays.asList(receiveEmail);
        String subject = getSubject();
        String emailVerificationHtml = getEmailVerificationHtml(authKey);

        EmailSenderDto emailSenderDto = EmailSenderDto.builder()
                .from(sender)
                .to(receiver)
                .subject(subject)
                .content(emailVerificationHtml)
                .build();

        return emailSenderDto;
    }

    private void confirmSentEmail(SendEmailResult sendEmailResult) {
        if (sendEmailResult.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            throw new FailSendEmailException();
        }
    }

}

package wayc.backend.member.domain.service;

public interface SendEmailService {
    ValidateEmailResponseDto sendVerificationEmail(String receiveEmail);
}

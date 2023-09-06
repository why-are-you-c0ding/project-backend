package wayc.backend.member.domain.service;

public interface SendEmailService {
    void sendVerificationEmail(String receiveEmail, String authKey);
}

package wayc.backend.member.domain.service;

import wayc.backend.member.presentation.dto.response.ValidateEmailResponse;

public interface SendEmailService {
    ValidateEmailResponse sendVerificationEmail(String receiveEmail);
}

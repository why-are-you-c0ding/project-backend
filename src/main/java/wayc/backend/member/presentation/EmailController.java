package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wayc.backend.member.application.EmailService;
import wayc.backend.member.presentation.dto.request.SendEmailRequest;
import wayc.backend.member.presentation.dto.request.ValidateEmailRequest;
import wayc.backend.member.presentation.dto.response.ValidateResponse;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    private final static String VERIFICATION_EMAIL_SUCCESS = "이메일 발송에 성공했습니다.";
    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";

    @PostMapping("/verification/email")
    public ResponseEntity<ValidateResponse> sendEmail(
            @RequestBody @Validated SendEmailRequest request
    ){
        emailService.sendVerificationEmail(request.getReceiveEmail());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_EMAIL_SUCCESS));
    }

    @PostMapping("/verification/email/auth-key")
    public ResponseEntity<ValidateResponse> verifyEmail(
            @RequestBody @Validated ValidateEmailRequest request
    ){
        emailService.verifyEmail(request.getEmail(), request.getAuthKey());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_SUCCESS));
    }
}

package wayc.backend.verification.presentation;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.verification.presentation.dto.request.PostSendEmailRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyNickNameRequestDto;
import wayc.backend.verification.presentation.dto.response.VerifyResponseDto;
import wayc.backend.verification.service.EmailService;
import wayc.backend.verification.service.VerificationService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;
    private final EmailService emailService;

    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";
    private final static String VERIFICATION_EMAIL_SUCCESS = "이메일 발송에 성공했습니다.";

    @PostMapping("/nick-name")
    public ResponseEntity<VerifyResponseDto> verifyNickName(@RequestBody @Validated PostVerifyNickNameRequestDto request){
        verificationService.ExistSameNickName(request.getNickName());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_SUCCESS));
    }

    @PostMapping("/login-id")
    public ResponseEntity<VerifyResponseDto> verifyLoginId(@RequestBody @Validated PostVerifyLoginIdRequestDto request){
        verificationService.ExistSamLoginId(request.getLoginId());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_SUCCESS));
    }

    @PostMapping("/email")
    public ResponseEntity<VerifyResponseDto> sendEmail(@RequestBody @Validated PostSendEmailRequestDto request){
        emailService.sendVerificationEmail(request.getReceiveEmail());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_EMAIL_SUCCESS));
    }
}

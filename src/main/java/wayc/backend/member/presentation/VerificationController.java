package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.member.presentation.dto.response.VerificationEmailInfoDto;
import wayc.backend.member.presentation.dto.request.PostSendEmailRequestDto;
import wayc.backend.member.presentation.dto.request.PostVerifyEmailRequestDto;
import wayc.backend.member.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.member.presentation.dto.request.PostVerifyNickNameRequestDto;
import wayc.backend.member.presentation.dto.response.VerifyResponseDto;
import wayc.backend.member.application.EmailService;
import wayc.backend.member.application.VerificationService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;
    private final EmailService emailService;

    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";
    private final static String VERIFICATION_EMAIL_SUCCESS = "이메일 발송에 성공했습니다.";

    @PostMapping("/nick-name")
    public ResponseEntity<VerifyResponseDto> verifyNickName(
            @RequestBody @Validated PostVerifyNickNameRequestDto request
    ){
        verificationService.ExistSameNickName(request.getNickName());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_SUCCESS));
    }

    @PostMapping("/login-id")
    public ResponseEntity<VerifyResponseDto> verifyLoginId(
            @RequestBody @Validated PostVerifyLoginIdRequestDto request
    ){
        verificationService.ExistSamLoginId(request.getLoginId());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_SUCCESS));
    }

    @PostMapping("/email")
    public ResponseEntity<VerifyResponseDto> sendEmail(
            @RequestBody @Validated PostSendEmailRequestDto request
    ){
        VerificationEmailInfoDto verificationEmailInfoDto = emailService.sendVerificationEmail(request.getReceiveEmail());
        verificationService.saveVerificationNumber(verificationEmailInfoDto);
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_EMAIL_SUCCESS));
    }

    @PostMapping("/email/auth-key")
    public ResponseEntity<VerifyResponseDto> verifyEmail(
            @RequestBody @Validated PostVerifyEmailRequestDto request
    ){
        verificationService.verifyEmail(request.getEmail(), request.getAuthKey());
        return ResponseEntity.ok(new VerifyResponseDto(VERIFICATION_SUCCESS));
    }
}

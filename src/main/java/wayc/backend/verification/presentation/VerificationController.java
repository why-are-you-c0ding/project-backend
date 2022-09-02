package wayc.backend.verification.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.verification.presentation.dto.request.PostVerifyLoginIdRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyNickNameRequestDto;
import wayc.backend.verification.presentation.dto.response.VerifyResponseDto;
import wayc.backend.verification.service.VerificationService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationService verificationService;

    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";

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
}

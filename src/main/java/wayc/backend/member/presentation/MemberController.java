package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.member.application.MemberProvider;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.presentation.dto.request.*;
import wayc.backend.member.presentation.dto.response.RegisterMemberResponse;
import wayc.backend.member.presentation.dto.response.ValidateResponse;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberProvider memberProvider;

    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";

    @PostMapping("/members/consumers")
    public ResponseEntity<RegisterMemberResponse> createMember(
            @RequestBody @Validated RegisterConsumerRequest request
    ){
        memberService.registerMember(RegisterConsumerRequest.toServiceDto(request));
        return ResponseEntity.ok(new RegisterMemberResponse());
    }

    @PostMapping("/members/sellers")
    public ResponseEntity<RegisterMemberResponse> createSeller(
            @RequestBody @Validated RegisterSellerRequest request
    ){
        memberService.registerMember(RegisterSellerRequest.toServiceDto(request));
        return ResponseEntity.ok(new RegisterMemberResponse());
    }
    
    @PostMapping("/verification/nick-name")
    public ResponseEntity<ValidateResponse> verifyNickName(
            @RequestBody @Validated ValidateNickNameRequest request
    ){
        memberProvider.validateNickName(request.getNickName());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_SUCCESS));
    }

    @PostMapping("/verification/login-id")
    public ResponseEntity<ValidateResponse> verifyLoginId(
            @RequestBody @Validated ValidateLoginIdRequest request
    ) {
        memberProvider.validateLoginId(request.getLoginId());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_SUCCESS));
    }
}

package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;
import wayc.backend.member.presentation.dto.request.*;
import wayc.backend.member.presentation.dto.response.RegisterMemberResponse;
import wayc.backend.member.presentation.dto.response.ValidateResponse;

import wayc.backend.cart.application.CartService;
import wayc.backend.shop.application.ShopService;


@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    //TODO 나중에 도메인 이벤트로 빼버리자.
    private final ShopService shopService;
    private final CartService cartService;

    private final static String VERIFICATION_SUCCESS = "검증에 성공했습니다.";

    @PostMapping("/members/consumers")
    public ResponseEntity<RegisterMemberResponse> createMember(
            @RequestBody @Validated RegisterConsumerRequest request
    ){
        RegisterMemberResponseDto res = memberService.registerMember(RegisterConsumerRequest.toServiceDto(request));
        cartService.create(res.getId());
        return ResponseEntity.ok(new RegisterMemberResponse());
    }

    @PostMapping("/members/sellers")
    public ResponseEntity<RegisterMemberResponse> createSeller(
            @RequestBody @Validated RegisterSellerRequest request
    ){
        RegisterMemberResponseDto res = memberService.registerMember(RegisterSellerRequest.toServiceDto(request));
        shopService.registerShop(res.getId(), res.getNickName());
        cartService.create(res.getId());
        return ResponseEntity.ok(new RegisterMemberResponse());
    }
    
    @PostMapping("/verification/nick-name")
    public ResponseEntity<ValidateResponse> verifyNickName(
            @RequestBody @Validated ValidateNickNameRequest request
    ){
        memberService.validateNickName(request.getNickName());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_SUCCESS));
    }

    @PostMapping("/verification/login-id")
    public ResponseEntity<ValidateResponse> verifyLoginId(
            @RequestBody @Validated ValidateLoginIdRequest request
    ) {
        memberService.validateLoginId(request.getLoginId());
        return ResponseEntity.ok(new ValidateResponse(VERIFICATION_SUCCESS));
    }
}

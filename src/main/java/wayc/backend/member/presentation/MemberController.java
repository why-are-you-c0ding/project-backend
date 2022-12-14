package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.cart.application.CartService;
import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.dto.request.PostConsumerRequestDto;
import wayc.backend.member.presentation.dto.request.PostSellerRequestDto;
import wayc.backend.member.presentation.dto.response.PostMemberResponseDto;
import wayc.backend.shop.application.ShopService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ShopService shopService;
    private final CartService cartService;

    @PostMapping("/consumers")
    public ResponseEntity<PostMemberResponseDto> createMember(
            @RequestBody @Validated PostConsumerRequestDto request
    ){
        CreateMemberResponseDto res = memberService.createConsumer(PostConsumerRequestDto.toServiceDto(request));
        cartService.create(res.getId());
        return ResponseEntity.ok(new PostMemberResponseDto());
    }

    @PostMapping("/sellers")
    public ResponseEntity<PostMemberResponseDto> createSeller(
            @RequestBody @Validated PostSellerRequestDto request
    ){
        CreateMemberResponseDto res = memberService.createSeller(PostSellerRequestDto.toServiceDto(request));
        shopService.createShop(res.getId(), res.getNickName());
        cartService.create(res.getId());
        return ResponseEntity.ok(new PostMemberResponseDto());
    }
}

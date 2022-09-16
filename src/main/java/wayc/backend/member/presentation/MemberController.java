package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.member.application.MemberService;
import wayc.backend.member.application.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.dto.request.PostConsumerRequestDto;
import wayc.backend.member.presentation.dto.request.PostSellerRequestDto;


@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/consumers")
    public ResponseEntity<CreateMemberResponseDto> createMember(
            @RequestBody @Validated PostConsumerRequestDto request
    ){
        CreateMemberResponseDto res = memberService.createConsumer(PostConsumerRequestDto.toServiceDto(request));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/sellers")
    public ResponseEntity<CreateMemberResponseDto> createSeller(
            @RequestBody @Validated PostSellerRequestDto request
    ){
        CreateMemberResponseDto res = memberService.createSeller(PostSellerRequestDto.toServiceDto(request));
        return ResponseEntity.ok(res);
    }
}

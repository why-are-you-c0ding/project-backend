package wayc.backend.member.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.member.business.MemberService;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.dto.request.PostMemberRequestDto;


@RequiredArgsConstructor
@RestController
//@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<CreateMemberResponseDto> createMember(@RequestBody @Validated PostMemberRequestDto request){
        CreateMemberResponseDto res = memberService.createMember(PostMemberRequestDto.toServiceDto(request));
        return ResponseEntity.ok(res);
    }

    @GetMapping()
    public String test(){
        return "test";
    }
}

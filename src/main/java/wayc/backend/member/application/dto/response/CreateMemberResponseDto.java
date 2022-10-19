package wayc.backend.member.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import wayc.backend.member.domain.Member;

@Getter
public class CreateMemberResponseDto {

    private Long id;
    private String nickName;
    private String email;
    private String loginId;
    private int age;

    public static CreateMemberResponseDto of(Member member) {
        return CreateMemberResponseDto.builder()
                .id(member.getId())
                .nickName(member.getNickName())
                .email(member.getEmail().getEmail())
                .loginId(member.getLoginId())
                .age(member.getAge())
                .build();
    }

    @Builder
    public CreateMemberResponseDto(Long id, String nickName, String email, String loginId, int age) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.age = age;
    }
}

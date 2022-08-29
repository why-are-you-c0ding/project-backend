package wayc.backend.member.business.dto.request;

import lombok.Builder;
import lombok.Getter;

import wayc.backend.member.domain.Member;

@Getter
public class CreateMemberRequestDto {

    private String nickName;
    private String email;
    private String loginId;
    private String password;
    private String checkPassword;
    private String age;

    @Builder
    public CreateMemberRequestDto(String nickName, String email, String loginId, String password, String checkPassword, String age) {
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.age = age;
    }

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .loginId(loginId)
                .password(password)
                .age(age).build();
    }
}

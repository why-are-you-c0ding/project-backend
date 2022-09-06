package wayc.backend.member.business.dto.request;

import lombok.Builder;
import lombok.Getter;

import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.member.domain.Member;

@Getter
public class CreateMemberRequestDto {

    private String nickName;
    private String email;
    private String loginId;
    private String password;
    private String checkPassword;
    private int age;

    @Builder
    public CreateMemberRequestDto(String nickName, String email, String loginId, String password, String checkPassword, int age) {
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.age = age;
    }

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .age(age).build();
    }
}

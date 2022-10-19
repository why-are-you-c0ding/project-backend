package wayc.backend.member.application.dto.request;

import lombok.Builder;
import lombok.Getter;

import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.Email;

import static wayc.backend.security.role.Role.*;

@Getter
public class CreateConsumerRequestDto extends AbstractCreateMemberRequestDto{

    @Builder
    public CreateConsumerRequestDto(String nickName, String email, String loginId, String password, String checkPassword, int age, String authKey) {
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.age = age;
        this.authKey = authKey;
    }

    public Member toEntity(PasswordEncoder passwordEncoder, Email email) {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .age(age)
                .role(ROLE_CONSUMER)
                .build();
    }
}

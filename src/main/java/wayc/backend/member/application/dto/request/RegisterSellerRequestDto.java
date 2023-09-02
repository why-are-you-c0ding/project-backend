package wayc.backend.member.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.member.domain.Member;

import static wayc.backend.security.role.Role.*;

@Getter
public class RegisterSellerRequestDto extends AbstractRegisterMemberRequestDto {

    @Builder
    public RegisterSellerRequestDto(String nickName, String email, String loginId, String password, String checkPassword, int age, String authKey) {
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.age = age;
        this.authKey = authKey;
    }

    @Override
    public Member toEntitySpecifically(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .age(age)
                .role(ROLE_SELLER)
                .build();
    }
}

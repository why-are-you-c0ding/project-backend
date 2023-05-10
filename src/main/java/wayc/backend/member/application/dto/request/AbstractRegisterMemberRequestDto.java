package wayc.backend.member.application.dto.request;

import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;

@Getter
public abstract class AbstractRegisterMemberRequestDto {

    protected String nickName;
    protected String email;
    protected String loginId;
    protected String password;
    protected String checkPassword;
    protected String authKey;
    protected int age;

    public Member toEntity(PasswordEncoder passwordEncoder, Email email){
        return toEntitySpecifically(passwordEncoder, email);
    }

    protected abstract Member toEntitySpecifically(PasswordEncoder passwordEncoder, Email email);
}

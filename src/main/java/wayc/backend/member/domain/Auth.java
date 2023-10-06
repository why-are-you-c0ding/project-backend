package wayc.backend.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class Auth {

    private String email;
    private String password;

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

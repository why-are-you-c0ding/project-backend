package wayc.backend.security.local;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalLoginRequest {

    private String loginId;
    private String password;

    public LocalLoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}

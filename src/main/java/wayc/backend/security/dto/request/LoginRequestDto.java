package wayc.backend.security.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String loginId;
    private String password;

    public LoginRequestDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}

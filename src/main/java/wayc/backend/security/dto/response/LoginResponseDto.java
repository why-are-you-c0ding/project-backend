package wayc.backend.security.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private String message;
    private String jwt;

    public LoginResponseDto(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }
}
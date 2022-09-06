package wayc.backend.security.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private String message;

    public LoginResponseDto(String message) {
        this.message = message;
    }
}

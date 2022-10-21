package wayc.backend.security.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LogoutResponseDto {

    private String message;

    public LogoutResponseDto() {
        this.message = "Logout Success.";
    }
}
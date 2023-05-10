package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidateEmailRequest {

    private String email;
    private String authKey;

    public ValidateEmailRequest(String email, String authKey) {
        this.email = email;
        this.authKey = authKey;
    }
}

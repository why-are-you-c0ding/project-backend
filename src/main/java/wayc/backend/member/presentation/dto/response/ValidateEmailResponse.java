package wayc.backend.member.presentation.dto.response;

import lombok.Getter;

@Getter
public class ValidateEmailResponse {

    private final String authKey;
    private final String email;

    public ValidateEmailResponse(String authKey, String email) {
        this.authKey = authKey;
        this.email = email;
    }
}

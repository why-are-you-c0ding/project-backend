package wayc.backend.verification.business.dto;

import lombok.Getter;

@Getter
public class VerificationEmailInfoDto {

    private final String authKey;
    private final String email;

    public VerificationEmailInfoDto(String authKey, String email) {
        this.authKey = authKey;
        this.email = email;
    }
}

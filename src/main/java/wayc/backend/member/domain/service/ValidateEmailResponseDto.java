package wayc.backend.member.domain.service;

import lombok.Getter;

@Getter
public class ValidateEmailResponseDto {

    private final String authKey;
    private final String email;

    public ValidateEmailResponseDto(String authKey, String email) {
        this.authKey = authKey;
        this.email = email;
    }
}

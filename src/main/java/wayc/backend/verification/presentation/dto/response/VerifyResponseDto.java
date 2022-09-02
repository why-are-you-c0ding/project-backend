package wayc.backend.verification.presentation.dto.response;

import lombok.Getter;


@Getter
public class VerifyResponseDto {

    private String message;

    public VerifyResponseDto(String message) {
        this.message = message;
    }
}

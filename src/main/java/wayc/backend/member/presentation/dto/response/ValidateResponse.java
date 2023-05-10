package wayc.backend.member.presentation.dto.response;

import lombok.Getter;


@Getter
public class ValidateResponse {

    private String message;

    public ValidateResponse(String message) {
        this.message = message;
    }
}

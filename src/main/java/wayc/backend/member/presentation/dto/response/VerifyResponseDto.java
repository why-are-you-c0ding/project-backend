package wayc.backend.member.presentation.dto.response;

import lombok.Getter;


@Getter
public class VerifyResponseDto {

    private String message;

    public VerifyResponseDto(String message) {
        this.message = message;
    }
}

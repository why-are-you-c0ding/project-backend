package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class ValidateNickNameRequest {

    @NotBlank
    private String nickName;

    public ValidateNickNameRequest(String nickName) {
        this.nickName = nickName;
    }
}

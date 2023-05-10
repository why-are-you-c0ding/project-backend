package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ValidateLoginIdRequest {

    @NotBlank
    private String loginId;

    //테스트용
    public ValidateLoginIdRequest(String loginId) {
        this.loginId = loginId;
    }
}

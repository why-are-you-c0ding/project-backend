package wayc.backend.verification.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostVerifyLoginIdRequestDto {

    @NotBlank
    private String loginId;

    //테스트용
    public PostVerifyLoginIdRequestDto(String loginId) {
        this.loginId = loginId;
    }
}

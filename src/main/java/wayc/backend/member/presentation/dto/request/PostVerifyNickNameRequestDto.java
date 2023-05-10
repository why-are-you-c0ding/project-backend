package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class PostVerifyNickNameRequestDto {

    @NotBlank
    private String nickName;

    public PostVerifyNickNameRequestDto(String nickName) {
        this.nickName = nickName;
    }
}

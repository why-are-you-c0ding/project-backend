package wayc.backend.verification.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostVerifyEmailRequestDto {

    private String email;
    private String authKey;
}

package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSendEmailRequestDto {

    private String receiveEmail;

    public PostSendEmailRequestDto(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }
}

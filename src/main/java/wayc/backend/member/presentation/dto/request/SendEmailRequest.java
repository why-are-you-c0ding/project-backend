package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendEmailRequest {

    private String receiveEmail;

    public SendEmailRequest(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }
}

package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class SendEmailRequest {

    @NotEmpty
    private String receiveEmail;

    public SendEmailRequest(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }
}

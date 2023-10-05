package wayc.backend.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommandSuccessResponse {

    private String message;

    public CommandSuccessResponse(String message) {
        this.message = message;
    }
}

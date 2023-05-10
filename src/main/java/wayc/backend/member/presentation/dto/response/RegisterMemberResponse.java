package wayc.backend.member.presentation.dto.response;

import lombok.Getter;

@Getter
public class RegisterMemberResponse {

    private String message;

    public RegisterMemberResponse() {
        this.message = "멤버 생성에 성공했습니다.";
    }
}

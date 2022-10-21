package wayc.backend.member.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostMemberResponseDto {

    private String message;

    public PostMemberResponseDto() {
        this.message = "멤버 생성에 성공했습니다.";
    }
}

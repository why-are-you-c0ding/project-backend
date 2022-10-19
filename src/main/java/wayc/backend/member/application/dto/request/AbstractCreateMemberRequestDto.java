package wayc.backend.member.application.dto.request;

import lombok.Getter;

@Getter
public abstract class AbstractCreateMemberRequestDto {
    protected String nickName;
    protected String email;
    protected String loginId;
    protected String password;
    protected String checkPassword;
    protected int age;
    protected String authKey;
}

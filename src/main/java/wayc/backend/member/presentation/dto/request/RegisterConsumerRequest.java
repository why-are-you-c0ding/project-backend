package wayc.backend.member.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;

import javax.validation.constraints.*;

@Getter
public class RegisterConsumerRequest {

    @Size(min = 2)
    private String nickName;

    @Email
    private String email;

    @Size(min = 6)
    private String loginId;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",
            message = "비밀번호는 최소 8 자로 문자, 숫자 및 특수 문자를 최소 하나씩 포함해서 8-15자리 이내로 입력해주세요.")
    private String password;

    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$",
            message = "비밀번호는 최소 8 자로 문자, 숫자 및 특수 문자를 최소 하나씩 포함해서 8-15자리 이내로 입력해주세요.")
    private String checkPassword;

    private int age;

    private String authKey;

    public static RegisterConsumerRequestDto toServiceDto(RegisterConsumerRequest request) {
        return RegisterConsumerRequestDto.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .checkPassword(request.getCheckPassword())
                .age(request.getAge())
                .authKey(request.getAuthKey())
                .build();
    }

    //테스트용
    @Builder
    public RegisterConsumerRequest(String nickName, String email, String loginId, String password, String checkPassword, int age, String authKey) {
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.age = age;
        this.authKey = authKey;
    }
}

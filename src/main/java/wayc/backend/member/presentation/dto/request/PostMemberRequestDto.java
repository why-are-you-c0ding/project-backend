package wayc.backend.member.presentation.dto.request;

import lombok.Getter;
import lombok.Setter;
import wayc.backend.member.business.dto.request.CreateMemberRequestDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PostMemberRequestDto {

    @NotBlank
    private String nickName;

    @Email
    private String email;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String checkPassword;

    private String age;

    public static CreateMemberRequestDto toServiceDto(PostMemberRequestDto request) {
        return CreateMemberRequestDto.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .checkPassword(request.getCheckPassword())
                .age(request.getAge())
                .build();
    }
}

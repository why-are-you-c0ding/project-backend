package wayc.backend.factory.member.dto;

import wayc.backend.member.application.dto.response.RegisterMemberResponseDto;

public class CreateMemberResponseDtoFactory {

    static public RegisterMemberResponseDto createSuccessCaseDto(){
        return RegisterMemberResponseDto.
                builder()
                .id(1L)
                .email("123@google.com")
                .nickName("nickName")
                .loginId("loginId")
                .age(23)
                .build();
    }
}
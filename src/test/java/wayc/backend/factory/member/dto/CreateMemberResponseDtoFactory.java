package wayc.backend.factory.member.dto;

import wayc.backend.member.application.dto.response.CreateMemberResponseDto;

public class CreateMemberResponseDtoFactory {

    static public CreateMemberResponseDto createSuccessCaseDto(){
        return CreateMemberResponseDto.
                builder()
                .id(1L)
                .email("123@google.com")
                .nickName("nickName")
                .loginId("loginId")
                .age(23)
                .build();
    }
}
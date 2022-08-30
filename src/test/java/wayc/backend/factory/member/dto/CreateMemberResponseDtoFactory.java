package wayc.backend.factory.member.dto;

import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.dto.request.PostMemberRequestDto;

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
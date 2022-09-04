package wayc.backend.factory.member.dto;

import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;

public class CreateMemberRequestDtoFactory {

    static public CreateMemberRequestDto createSuccessCaseDto(){
        return CreateMemberRequestDto.builder()
                .nickName("nickName")
                .email("123@gmail.com")
                .loginId("loginId")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .age(24)
                .build();
    }
}
package wayc.backend.factory.member.dto;

import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;

public class CreateMemberRequestDtoFactory {

    static public CreateConsumerRequestDto createSuccessCaseDto(){
        return CreateConsumerRequestDto.builder()
                .nickName("nickName")
                .email("123@gmail.com")
                .loginId("loginId")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .age(24)
                .build();
    }
}
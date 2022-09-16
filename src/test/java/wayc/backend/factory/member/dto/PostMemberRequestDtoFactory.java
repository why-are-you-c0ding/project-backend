package wayc.backend.factory.member.dto;

import wayc.backend.member.presentation.dto.request.PostConsumerRequestDto;

public class PostMemberRequestDtoFactory {

    static public PostConsumerRequestDto createSuccessCaseDto(){
        return PostConsumerRequestDto.
                builder()
                .email("123@google.com")
                .nickName("nickName")
                .loginId("loginId")
                .password("1q2w3e4r!!!")
                .checkPassword("1q2w3e4r!!!")
                .age(23)
                .build();
    }
}
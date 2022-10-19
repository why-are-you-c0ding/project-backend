package wayc.backend.factory.member.dto;

import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.member.application.dto.request.CreateSellerRequestDto;

public class CreateMemberRequestDtoFactory {

    static public CreateConsumerRequestDto createSuccessConsumerDto(){
        return CreateConsumerRequestDto.builder()
                .nickName("nickName")
                .email("123@gmail.com")
                .loginId("loginId")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .age(24)
                .authKey("999999")
                .build();
    }

    static public CreateSellerRequestDto createSuccessSellerDto(){
        return CreateSellerRequestDto.builder()
                .nickName("nickName")
                .email("123@gmail.com")
                .loginId("loginId")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .age(24)
                .authKey("999999")
                .build();
    }
}
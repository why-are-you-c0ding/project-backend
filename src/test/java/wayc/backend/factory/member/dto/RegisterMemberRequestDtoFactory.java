package wayc.backend.factory.member.dto;

import wayc.backend.member.application.dto.request.RegisterConsumerRequestDto;
import wayc.backend.member.application.dto.request.RegisterSellerRequestDto;

public class RegisterMemberRequestDtoFactory {

    static public RegisterConsumerRequestDto createSuccessConsumerDto(){
        return RegisterConsumerRequestDto.builder()
                .nickName("nickName")
                .email("123@gmail.com")
                .loginId("loginId")
                .password("1q2w3e4r!")
                .checkPassword("1q2w3e4r!")
                .age(24)
                .authKey("999999")
                .build();
    }

    static public RegisterSellerRequestDto createSuccessSellerDto(){
        return RegisterSellerRequestDto.builder()
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
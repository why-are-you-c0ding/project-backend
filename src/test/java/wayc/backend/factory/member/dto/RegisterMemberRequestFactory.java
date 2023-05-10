package wayc.backend.factory.member.dto;

import wayc.backend.member.presentation.dto.request.RegisterConsumerRequest;
import wayc.backend.member.presentation.dto.request.RegisterSellerRequest;

public class RegisterMemberRequestFactory {

    static public RegisterConsumerRequest createConsumerSuccessCaseDto(){
        return RegisterConsumerRequest.
                builder()
                .email("123@google.com")
                .nickName("nickName")
                .loginId("loginId")
                .password("1q2w3e4r!!!")
                .checkPassword("1q2w3e4r!!!")
                .authKey("954721")
                .age(23)
                .build();
    }

    static public RegisterSellerRequest createSellerSuccessCaseDto(){
        return RegisterSellerRequest.
                builder()
                .email("123@google.com")
                .nickName("nickName")
                .loginId("loginId")
                .password("1q2w3e4r!!!")
                .checkPassword("1q2w3e4r!!!")
                .authKey("954721")
                .age(23)
                .build();
    }
}
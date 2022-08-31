package wayc.backend.factory.member.domain;

import wayc.backend.member.domain.Member;

public class MemberFactory {

    public static Member createSuccessCase(){
        return Member.builder()
                .email("123@gamil.com")
                .nickName("nickName")
                .loginId("loginId")
                .password("1q2w3e4r!!")
                .age(23)
                .build();
    }
}

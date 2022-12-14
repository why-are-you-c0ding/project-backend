package wayc.backend.factory.member.domain;

import wayc.backend.member.domain.Email;
import wayc.backend.member.domain.Member;
import wayc.backend.security.role.Role;

import static wayc.backend.security.role.Role.*;

public class MemberFactory {

    public static Member createConsumerSuccessCase(){
        return Member.builder()
                .email(new Email("123@gamil.com", "999999"))
                .nickName("nickName")
                .loginId("loginId")
                .password("1q2w3e4r!!")
                .age(23)
                .role(ROLE_CONSUMER)
                .build();
    }
}

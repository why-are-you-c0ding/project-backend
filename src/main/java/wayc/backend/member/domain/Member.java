package wayc.backend.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String email;
    private String loginId;
    private String password;
    private int age;

    @Builder
    public Member(String nickName, String email, String loginId, String password, int age) {
        super();
        this.nickName = nickName;
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.age = age;
    }
}

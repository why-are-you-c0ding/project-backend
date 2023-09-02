package wayc.backend.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.event.Events;
import wayc.backend.member.domain.event.MemberRegisteredEvent;
import wayc.backend.security.role.Role;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String loginId;

    private String password;

    private int age;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    //추후에 멤버에서 장바구니를 추적해야하거나, 샵을 추적해야하면 아이디를 추가하자.

    @Builder
    public Member(String nickName, String loginId, String password, int age, String email, Role role) {
        this.nickName = nickName;
        this.loginId = loginId;
        this.password = password;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public void registered() {
        Events.raise(new MemberRegisteredEvent(id, nickName, role));
    }
}

//도메인과 엔티티에 절대 서비스 정책을 넣지 말자.
//추후에는 멤버와 판매자를 분리하도록 하자...
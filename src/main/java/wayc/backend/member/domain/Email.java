package wayc.backend.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.domain.BaseStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String authKey;

    private int verificationCountPerDay;  //TODO 추후에 로직을 구현하자.

    public Email(String email, String authKey) {
        this.email = email;
        this.authKey = authKey;
        this.verificationCountPerDay = 0;
    }
}

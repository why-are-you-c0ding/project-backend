package wayc.backend.member.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.event.Event;
import wayc.backend.security.role.Role;

@Getter
@NoArgsConstructor
public class MemberRegisteredEvent extends Event {

    private Long memberId;
    private String memberName;
    private Role role;

    public MemberRegisteredEvent(Long memberId,
                                 String memberName,
                                 Role role) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.role = role;
    }
}

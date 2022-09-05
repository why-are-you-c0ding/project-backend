package wayc.backend.security.context;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wayc.backend.member.domain.Member;

import java.util.Collection;

public class MemberContext implements UserDetails{

    private final Member member;

    public MemberContext(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member getMember() {
        return member;
    }
}

/**
 *  계정 만료, 계정 잠금, 자격 증명 만료, 계정 비활성화
 *  이런 기능 구현이 필요가 없으므로 다 true를 리턴
 */
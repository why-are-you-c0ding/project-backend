package wayc.backend.security.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import wayc.backend.member.domain.Member;
import wayc.backend.security.context.MemberContext;
import wayc.backend.member.application.VerificationService;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final VerificationService verificationService;

    @Override
    public UserDetails loadUserByUsername(String loginId) {
         Member member = verificationService.findByLoginId(loginId);

        String role = member.getRole().getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new MemberContext(member, authority);
    }
}

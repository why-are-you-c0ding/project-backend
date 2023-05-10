package wayc.backend.security.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.NotExistsMemberException;
import wayc.backend.security.context.MemberContext;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) {
         Member member = memberRepository.findByLoginIdAndStatus(loginId).orElseThrow(NotExistsMemberException::new);

        String role = member.getRole().getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

        return new MemberContext(member, authority);
    }
}

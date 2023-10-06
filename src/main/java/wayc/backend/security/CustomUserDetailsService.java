package wayc.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wayc.backend.member.domain.Member;
import wayc.backend.member.domain.repository.MemberRepository;
import wayc.backend.member.exception.NotExistsMemberException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginIdAndStatus(loginId)
                .orElseThrow(NotExistsMemberException::new);
        return UserPrincipal.create(member);
    }

    public UserDetails loadUserById(Long id) {
        Member member = memberRepository.findMemberById(id).orElseThrow(NotExistsMemberException::new);
        return UserPrincipal.create(member);
    }
}
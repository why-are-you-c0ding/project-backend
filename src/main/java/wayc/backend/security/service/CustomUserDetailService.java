package wayc.backend.security.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import wayc.backend.member.domain.Member;
import wayc.backend.security.context.MemberContext;
import wayc.backend.verification.business.VerificationService;

@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final VerificationService verificationService;

    @Override
    public UserDetails loadUserByUsername(String loginId) {
        Member member = verificationService.findByLoginId(loginId);

        //TODO 권한도 관련해서 때려 박아야함.

        return new MemberContext(member);
    }
}

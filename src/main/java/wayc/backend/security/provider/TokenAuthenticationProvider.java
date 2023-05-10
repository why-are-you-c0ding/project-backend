package wayc.backend.security.provider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import wayc.backend.member.exception.NotSamePasswordException;
import wayc.backend.security.context.MemberContext;
import wayc.backend.security.token.JwtAuthenticationToken;

@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication){
        String loginId = authentication.getName();
        String password = authentication.getCredentials().toString();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(loginId);

        if(isNotSamePassword(password, memberContext.getPassword())){
            throw new NotSamePasswordException();
        }

        return new JwtAuthenticationToken(
                memberContext.getMemberId(),
                null,
                memberContext.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

    private boolean isNotSamePassword(String rawPassword, String encoderPassword){
        return !passwordEncoder.matches(rawPassword, encoderPassword); //rawPassword, encoderPassword 순으로 인자를 넣는다.
    }
}

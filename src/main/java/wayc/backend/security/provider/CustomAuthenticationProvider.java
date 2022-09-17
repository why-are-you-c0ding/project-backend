package wayc.backend.security.provider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import wayc.backend.exception.verification.NotSamePasswordException;
import wayc.backend.security.context.MemberContext;
import wayc.backend.security.token.AjaxAuthenticationToken;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String password = authentication.getCredentials().toString();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(loginId);

        if(isNotSamePassword(password, memberContext.getPassword())){
            throw new NotSamePasswordException();
        }

        return new AjaxAuthenticationToken(
                //String.valueOf(memberContext.getMember().getId()),  //아이디를 리턴하도록 바꿨
                loginId,
                password,
                memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }

    private boolean isNotSamePassword(String rawPassword, String encoderPassword){
        return !passwordEncoder.matches(rawPassword, encoderPassword); //rawPassword, encoderPassword 순으로 인자를 넣는다.
    }
}

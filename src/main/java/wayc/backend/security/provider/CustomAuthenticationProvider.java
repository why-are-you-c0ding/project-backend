package wayc.backend.security.provider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import wayc.backend.exception.verification.NotSamePasswordException;
import wayc.backend.security.token.AjaxAuthenticationToken;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        if(isNotSamePassword(password, userDetails.getPassword())){
            throw new NotSamePasswordException();
        }

        return new AjaxAuthenticationToken(loginId, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }

    private boolean isNotSamePassword(String rawPassword, String encoderPassword){
        return !passwordEncoder.matches(rawPassword, encoderPassword); //rawPassword, encoderPassword 순으로 인자를 넣는다.
    }
}

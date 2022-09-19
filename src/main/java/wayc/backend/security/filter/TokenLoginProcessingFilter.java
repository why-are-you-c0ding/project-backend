package wayc.backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import wayc.backend.security.dto.request.LoginRequestDto;
import wayc.backend.security.token.JwtAuthenticationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public TokenLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        LoginRequestDto loginDto = objectMapper.readValue(request.getReader(), LoginRequestDto.class);

        validateLoginRequestDto(loginDto);

        JwtAuthenticationToken token = new JwtAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }

    private void validateLoginRequestDto(
            LoginRequestDto loginDto
    ) {
        if (!StringUtils.hasText(loginDto.getLoginId()) || !StringUtils.hasText(loginDto.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
    }

    //인증 성공하면 리턴함.
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
}

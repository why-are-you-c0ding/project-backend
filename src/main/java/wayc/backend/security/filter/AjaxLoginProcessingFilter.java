package wayc.backend.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import wayc.backend.security.dto.request.LoginRequestDto;
import wayc.backend.security.token.AjaxAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(isNotAjaxLoginRequest(request.getHeader(X_REQUESTED_WITH), request.getMethod())){
            throw new RuntimeException("AJAX 로그인 요청이 아니다");
        }

        LoginRequestDto loginDto = objectMapper.readValue(request.getReader(), LoginRequestDto.class);

        validateLoginRequestDto(loginDto);

        AjaxAuthenticationToken token = new AjaxAuthenticationToken(loginDto.getLoginId(), loginDto.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }

    private void validateLoginRequestDto(LoginRequestDto loginDto) {
        if (!StringUtils.hasText(loginDto.getLoginId()) || !StringUtils.hasText(loginDto.getPassword())) {
            throw new AuthenticationServiceException("Username or Password not provided");
        }
    }

    private boolean isNotAjaxLoginRequest(String requestHeader, String requestMethod){
        return !XML_HTTP_REQUEST.equals(requestHeader) || !HttpMethod.POST.matches(requestMethod);
    }
}

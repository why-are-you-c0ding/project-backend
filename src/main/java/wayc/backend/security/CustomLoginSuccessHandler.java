package wayc.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import wayc.backend.common.CommandSuccessResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final String SUCCESS_MESSAGE = "로그인을 성공했습니다.";

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        UserPrincipal userPrincipal = (UserPrincipal) authentication;

        HttpSession session = request.getSession();
        session.setAttribute("userId", userPrincipal.getId());
        mapper.writeValue(response.getWriter(), new CommandSuccessResponse(SUCCESS_MESSAGE));

    }
}

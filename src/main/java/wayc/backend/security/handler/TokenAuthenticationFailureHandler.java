package wayc.backend.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private ObjectMapper mapper = new ObjectMapper();
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		String errorMessage = "Invalid Username or Password";

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		if(exception instanceof BadCredentialsException) {
			errorMessage = "Invalid Username or Password";
		} else if(exception instanceof DisabledException) {
			errorMessage = "Locked";
		} else if(exception instanceof CredentialsExpiredException) {
			errorMessage = "Expired password";
		}

		mapper.writeValue(response.getWriter(), errorMessage);
	}
}

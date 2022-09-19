package wayc.backend.security.service;

import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String createToken(Authentication authentication);
    //Authentication getAuthentication(String token)
    //boolean validateToken(String token)
}

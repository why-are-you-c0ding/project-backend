package wayc.backend.security.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import wayc.backend.security.context.MemberContext;
import wayc.backend.security.token.JwtAuthenticationToken;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProviderImpl implements JwtProvider{

    private final String secretKey;
    private final long expirationTimeInMilliSeconds;

    public JwtProviderImpl(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.token-validity-in-seconds}") long expirationTimeInMilliSeconds
    ) {
        this.secretKey = secretKey;
        this.expirationTimeInMilliSeconds = expirationTimeInMilliSeconds;
    }

    public String createToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();
        Date validity = new Date(now + this.expirationTimeInMilliSeconds);

        return Jwts.builder()
                .setSubject("token")
                .claim("id", ((JwtAuthenticationToken)authentication).getId())
                .claim("authorities", authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

}

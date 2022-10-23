package wayc.backend.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import wayc.backend.security.jwt.JwtProvider;
import wayc.backend.security.token.JwtAuthenticationToken;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProviderImpl implements JwtProvider {

    private final String secretKey;
    private final long expirationTimeInMilliSeconds;
    private final Key key;

    private static final String AUTHORITIES = "authorities";
    private static final String ID = "id";

    public JwtProviderImpl(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.token-validity-in-seconds}") long expirationTimeInMilliSeconds
    ) {
        this.secretKey = secretKey;
        this.expirationTimeInMilliSeconds = expirationTimeInMilliSeconds;
        this.key = createKey(secretKey);
    }

    public Key createKey(String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        for (byte keyByte : keyBytes) {
            System.out.print(keyByte);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String createToken(Authentication authentication) {
        String authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.expirationTimeInMilliSeconds);

        return Jwts.builder()
                .setSubject("token")
                .claim(ID, authentication.getPrincipal())
                .claim(AUTHORITIES, authorities)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new JwtAuthenticationToken(
                Long.valueOf((String.valueOf(claims.get(ID)))),
                null,
                authorities
        );
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 이렇게 자세히 예외를 잡을 수도 있지만, 의외로 예외는 모호하게 잡는게 보안상 좋다고 한다.
     * catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
     *          logger.info("잘못된 JWT 서명입니다.");
     *       } catch (ExpiredJwtException e) {
     *          logger.info("만료된 JWT 토큰입니다.");
     *       } catch (UnsupportedJwtException e) {
     *          logger.info("지원되지 않는 JWT 토큰입니다.");
     *       } catch (IllegalArgumentException e) {
     *          logger.info("JWT 토큰이 잘못되었습니다.");
     *       }
     */

    public Key getKey() {
        return key;
    }
}


//은근 deprecated 된 게 많네..
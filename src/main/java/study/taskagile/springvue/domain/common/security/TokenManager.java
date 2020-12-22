package study.taskagile.springvue.domain.common.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class TokenManager {

    private Key secretKey;

    public TokenManager(@Value("${app.token-secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String jwt(Long userId) {
        return Jwts.builder()
            .setSubject(String.valueOf(userId))
            .signWith(secretKey).compact();
    }

    public Long verifyJwt(String jsw) {
        return Long.valueOf(
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jsw)
                .getBody()
                .getSubject()
        );
    }
}

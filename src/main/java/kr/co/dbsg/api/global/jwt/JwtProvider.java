package kr.co.dbsg.api.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.crypto.SecretKey;
import kr.co.dbsg.api.global.config.properties.JwtProperty;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final JwtProperty jwtProperty;
    private final SecretKey key;
    private final MacAlgorithm alg;

    public JwtProvider(JwtProperty jwtProperty, MacAlgorithm macAlgorithm) {
        this.jwtProperty = jwtProperty;
        this.alg = macAlgorithm;

        var keyBytes = Decoders.BASE64.decode(jwtProperty.secret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String create(long userId) {
        final LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(jwtProperty.expiration());
        Date exp = Timestamp.valueOf(localDateTime);

        return Jwts.builder()
            .issuer(jwtProperty.issuer())
            .expiration(exp)
            .claim("uid", userId)
            .signWith(key, alg)
            .compact();
    }

    public Long verify(String compactJws) {
        try {
            if (compactJws == null) {
                throw new JwtException("토큰은 null일 수 없습니다.");
            }

            final Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(compactJws);

            return Long.parseLong(claimsJws.getPayload().get("uid").toString());
        } catch (JwtException e) {
            throw new AuthenticationException(e.getMessage() + " " + compactJws, e.getCause());
        }
    }
}

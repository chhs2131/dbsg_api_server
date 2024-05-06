package kr.co.dbsg.api.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.validation.constraints.NotNull;
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
        Date exp = createExpireDate(jwtProperty.expiration());

        return Jwts.builder()
            .issuer(jwtProperty.issuer())
            .expiration(exp)
            .claim("uid", userId)
            .signWith(key, alg)
            .compact();
    }

    private Date createExpireDate(long expireSecond) {
        final long expireMilli = expireSecond * 1000;
        return new Date(System.currentTimeMillis() + expireMilli);
    }

    public Long verify(@NotNull String compactJws) {
        try {
            final Claims payload = getClaims(compactJws);
            return Long.parseLong(payload.get("uid").toString());
        } catch (JwtException e) {
            throw new AuthenticationException(e.getMessage() + " " + compactJws, e.getCause());
        }
    }

    public Date getExpiration(String compactJws) {
        return getClaims(compactJws).getExpiration();
    }

    private Claims getClaims(final String compactJws) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(compactJws)
            .getPayload();
    }
}

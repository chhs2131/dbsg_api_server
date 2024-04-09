package kr.co.dbsg.api.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.sql.Date;
import java.time.LocalDate;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    private final SecretKey key;

    public JwtProvider() {
        // TODO SECRET KEY property로 빼기
        final byte[] keyBytes = Decoders.BASE64.decode("das7f6asd76fvh7826v3923a78f6vh982a36vf872h93a6h98a2376h");
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String create(String userLoginType, long userId) {
        return Jwts.builder()
            .header()
            .keyId("todo_encrypt_key_for_user_id")
            .and()

            .issuer("api.dbsg.co.kr")
            .issuedAt(Date.valueOf(LocalDate.now()))
            .expiration(Date.valueOf(LocalDate.now().plusDays(10)))

            .claim("utype", userLoginType)
            .claim("uid", userId)

            .signWith(key)
            .compact();
    }

    public Long verify(String compactJws) {
        try {
            // null 체크
            if (compactJws == null) {
                throw new JwtException("토큰은 null일 수 없습니다.");
            }

            // bearer 체크
            if (!compactJws.startsWith("Bearer ")) {
                throw new JwtException("Bearer 토큰이 아닙니다.");
            }
            compactJws = compactJws.substring("Bearer ".length());

            // jwt 검증
            final Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(compactJws);

            // 유효시간도 확인

            // return
            return Long.parseLong(claimsJws.getPayload().get("uid").toString());
        } catch (JwtException e) {
            // TODO 미인증 사용자 에러에 대한 처리 !!
            throw new AuthenticationException();
        }
    }
}

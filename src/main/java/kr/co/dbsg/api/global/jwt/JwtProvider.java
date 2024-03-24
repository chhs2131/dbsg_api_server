package kr.co.dbsg.api.global.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.sql.Date;
import java.time.LocalDate;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey key = Jwts.SIG.HS256.key().build();

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

    public void verify(String compactJws) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(compactJws);
            //TODO OK, we can trust this JWT
        } catch (JwtException e) {
            //TODO don't trust the JWT!
        }
    }
}

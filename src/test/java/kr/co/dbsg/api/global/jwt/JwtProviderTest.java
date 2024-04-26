package kr.co.dbsg.api.global.jwt;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts.SIG;
import java.io.IOException;
import java.util.Base64;
import kr.co.dbsg.api.global.config.properties.JwtProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtProviderTest {
    private JwtProvider jwtProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        final JwtProperty jwtProperty = new JwtProperty(
            "Cx4EiY3FiBdQW4webwUfu6wFwj8CUlv9lPi0JNMnyuTsl2cIylHgb5m6mN1AuQe5ERzgS12SxJSnc9pM8Gug",
            "auth.test.com",
            86400
        );

        jwtProvider = new JwtProvider(jwtProperty, SIG.HS256);
    }

    @Test
    void 토큰생성테스트_성공() {
        long userId = 1;

        final String token = jwtProvider.create(userId);
        final Long verifyUserId = jwtProvider.verify(token);

        assertThat(verifyUserId).isEqualTo(userId);
    }

    @Test
    void 토큰검증테스트_다른SecretKey_실패() {
        long userId = 123;
        final JwtProperty 또다른토큰생성기 = new JwtProperty(
            "aabbccddeeffgghhiijjkk1122334455667788990011223344lHgb5m6mN1AuQe5ERzgS12SxJSnc9pM8Gug",
            "auth.test.com",
            987654321
        );
        final String token = new JwtProvider(또다른토큰생성기, SIG.HS256).create(userId);

        assertThrows(AuthenticationException.class, () ->
            jwtProvider.verify(token)
        );
    }

    @Test
    void 토큰검증테스트_손상된토큰_실패() {
        long userId = 1;

        String token = jwtProvider.create(userId);
        final char[] tokenChar = token.toCharArray();
        tokenChar[0] = 'a';
        tokenChar[1] = 'b';
        tokenChar[2] = 'c';
        tokenChar[3] = 'd';
        tokenChar[4] = 'e';
        final String tokenWithNoise = String.valueOf(tokenChar);

        assertThrows(AuthenticationException.class, () ->
            // Malformed protected header JSON: Unable to deserialize: Unrecognized token 'i': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
            jwtProvider.verify(tokenWithNoise)
        );
    }

    @Test
    void 토큰검증테스트_변조된토큰_실패() throws IOException {
        long userId = 1;
        String token = jwtProvider.create(userId);

        final String modulationToken = 토큰_Payload_변조(token, 1234L);

        assertThrows(AuthenticationException.class, () ->
            // JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
            jwtProvider.verify(modulationToken)
        );
    }

    private String 토큰_Payload_변조(String token, Long newUid) throws IOException {
        final String[] split = token.split("\\.");
        final byte[] decodedPayload = Base64.getDecoder().decode(split[1]);

        // 변조
        final TokenPayload tokenPayload = objectMapper.readValue(decodedPayload, TokenPayload.class);
        tokenPayload.uid = newUid;
        final byte[] payloadJson = objectMapper.writeValueAsBytes(tokenPayload);
        final String modulationPayload = Base64.getEncoder().encodeToString(payloadJson);

        final String modulationToken = String.join(".", split[0], modulationPayload, split[2]);
        return modulationToken;
    }

    public static class TokenPayload{
        public String iss;
        public Long exp;
        public Long uid;
    }

    @Test
    void 토큰검증테스트_유효기간만료_실패() {
        long userId = 123;
        final JwtProperty 만료토큰생성기 = new JwtProperty(
            "Cx4EiY3FiBdQW4webwUfu6wFwj8CUlv9lPi0JNMnyuTsl2cIylHgb5m6mN1AuQe5ERzgS12SxJSnc9pM8Gug",
            "auth.test.com",
            -100
        );
        final String token = new JwtProvider(만료토큰생성기, SIG.HS256).create(userId);

        assertThrows(AuthenticationException.class, () ->
            // JWT expired 101041 milliseconds ago at 2024-04-22T15:48:36.000Z. Current time: 2024-04-22T15:50:17.041Z. Allowed clock skew: 0 milliseconds.
            jwtProvider.verify(token)
        );
    }

    @Test
    void 토큰검증테스트_null_실패() {
        String token = null;

        assertThrows(IllegalArgumentException.class, () ->
            // 토큰은 null일 수 없습니다.
            jwtProvider.verify(token)
        );
    }
}
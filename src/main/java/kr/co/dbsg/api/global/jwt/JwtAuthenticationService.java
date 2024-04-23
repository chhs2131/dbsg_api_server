package kr.co.dbsg.api.global.jwt;

import java.time.LocalDateTime;
import kr.co.dbsg.api.api.auth.dto.UserTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Deprecated(since = "없어도 될듯 굳이")
public class JwtAuthenticationService {
    private final JwtProvider jwtProvider;

    public UserTokenResponse login(Long userId) {
        final String jwt = jwtProvider.create(userId);

        return new UserTokenResponse(
            userId,
            jwt,
            "NO_REFRESH_TOKEN",
            LocalDateTime.now(),  // TODO
            LocalDateTime.now()
        );
    }

    public Long verify(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer 토큰이 아닙니다.");
        }
        token = token.substring("Bearer ".length());

        return jwtProvider.verify(token);
    }
}

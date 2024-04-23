package kr.co.dbsg.api.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.dbsg.api.global.jwt.AuthenticationException;
import kr.co.dbsg.api.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authorization = request.getHeader("Authorization");
        final String token = getBearerToken(authorization);

        final Long userId = jwtProvider.verify(token);

        request.setAttribute("userId", userId);
        return true;
    }

    private String getBearerToken(final String bearer) {
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer 토큰이 아닙니다.");
        }
        return bearer.substring("Bearer ".length());
    }
}

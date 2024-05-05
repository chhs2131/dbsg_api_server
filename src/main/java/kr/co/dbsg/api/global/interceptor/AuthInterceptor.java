package kr.co.dbsg.api.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.dbsg.api.api.member.domain.Member;
import kr.co.dbsg.api.global.jwt.AuthenticationException;
import kr.co.dbsg.api.global.jwt.JwtProvider;
import kr.co.dbsg.api.global.jwt.JwtUserDetailService;
import kr.co.dbsg.api.global.resolver.LoginUser;
import kr.co.dbsg.api.global.security.AuthenticationContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final JwtUserDetailService jwtUserDetailService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!isAuthMethod(handler)) {
            return true;
        }

        final Long userId = verifyToken(request.getHeader("Authorization"));
        final Member member = jwtUserDetailService.loadUserById(userId);

        AuthenticationContextHolder.setContext(member);
        return true;
    }

    private Long verifyToken(final String authorization) {
        final String token = getBearerToken(authorization);
        return jwtProvider.verify(token);
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
        AuthenticationContextHolder.clear();
    }

    private boolean isAuthMethod(final Object handler) {
        if (handler instanceof ResourceHttpRequestHandler) {
            return false;  // view 관련 파일 제외
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.hasMethodAnnotation(LoginUser.class)) {
            return true;
        }

        return false;
    }

    private String getBearerToken(final String bearer) {
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer 토큰이 아닙니다.");
        }
        return bearer.substring("Bearer ".length());
    }
}

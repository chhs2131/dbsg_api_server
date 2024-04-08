package kr.co.dbsg.api.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import kr.co.dbsg.api.api.member.entity.MemberEntity;
import kr.co.dbsg.api.api.member.repository.MemberRepository;
import kr.co.dbsg.api.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        final String authorization = request.getHeader("Authorization");
        final Long userId = jwtProvider.verify(authorization);

        request.setAttribute("userId", userId);
        return true;
    }
}

package kr.co.dbsg.api.global.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain)
        throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        final String requestURI = httpRequest.getRequestURI();
        final String authorization = httpRequest.getHeader("Authorization");
        boolean isAuthenticated = checkAuthentication(requestURI, authorization);

        if (!isAuthenticated) {
            final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한이 없습니다.");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkAuthentication(String requestUri, String authorizationHeader) {
        // 패턴에 해당되지 않는, 권한이 필요없는 URI는 true 반환
        if (!requestUri.matches("/v1/events/[0-9]+/like")) {
            return true;
        }

        // 패턴에 해당할때는 반드시 bearer 토큰이 함께 잇을 것
        if (authorizationHeader != null && authorizationHeader.matches("^bearer .*")) {
            return true;
        }

        return false;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

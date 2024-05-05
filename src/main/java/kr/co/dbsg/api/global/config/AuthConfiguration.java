package kr.co.dbsg.api.global.config;

import kr.co.dbsg.api.global.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ps. 정규식 사용을 위해선 {} 필요 -> https://velog.io/@ksk7584/PathPattern#%EC%84%A4%EB%AA%85
        registry.addInterceptor(authInterceptor)
            .order(1)
            .addPathPatterns("/**");
    }
}

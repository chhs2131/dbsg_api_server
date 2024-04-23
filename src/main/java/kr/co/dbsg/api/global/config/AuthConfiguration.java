package kr.co.dbsg.api.global.config;

import java.util.List;
import kr.co.dbsg.api.global.filter.AuthFilter;
import kr.co.dbsg.api.global.interceptor.AuthInterceptor;
import kr.co.dbsg.api.global.resolver.AuthResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;
    private final AuthResolver authResolver;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ps. 정규식 사용을 위해선 {} 필요 -> https://velog.io/@ksk7584/PathPattern#%EC%84%A4%EB%AA%85
        registry.addInterceptor(authInterceptor)
            .order(1)
            .addPathPatterns("/v1/members/me")
            .addPathPatterns("/v1/events/{path:[0-9]+}/like");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authResolver);
    }
}

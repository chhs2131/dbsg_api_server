package kr.co.dbsg.api.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    public static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI openAPI() {
        // Swagger 정보 설정
        Info info = new Info()
            .version("v1.0.1")
            .title("공모주토피아 API")
            .description("https://www.github.com/chhs2131");

        // security 설정
        Components components = new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, getSecuritySchemesItem(SECURITY_SCHEME_NAME));

        // Bean 등록
        return new OpenAPI()
            .info(info)
            .components(components);
    }

    private SecurityScheme getSecuritySchemesItem(final String jwt) {
        return new SecurityScheme()
            .name(jwt)
            .type(SecurityScheme.Type.HTTP)
            .in(In.HEADER)
            .description("API Token")
            .scheme("bearer")
            .bearerFormat("JWT");
    }
}

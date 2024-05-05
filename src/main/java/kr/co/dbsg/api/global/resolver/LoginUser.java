package kr.co.dbsg.api.global.resolver;

import static kr.co.dbsg.api.global.config.SwaggerConfiguration.SECURITY_SCHEME_NAME;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(security = @SecurityRequirement(name = SECURITY_SCHEME_NAME))
public @interface LoginUser {
}

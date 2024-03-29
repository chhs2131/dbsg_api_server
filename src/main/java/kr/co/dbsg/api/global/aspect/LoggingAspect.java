package kr.co.dbsg.api.global.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Profile({"local", "dev"})
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* kr.co.dbsg.api.api..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String name = joinPoint.getSignature().getDeclaringTypeName();
        String path = request.getServletPath();
        String method = request.getMethod();

        long start = System.currentTimeMillis();
        log.info("Request ==> {} {} ({})", method, path, name);

        final Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
        log.info("Done <== {} {} (spend: {}ms)", method, path, end - start);

        return proceed;
    }
}

package kr.co.dbsg.api.global.config;

import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {
    @Bean
    public MacAlgorithm macAlgorithm() {
        return SIG.HS256;
    }
}

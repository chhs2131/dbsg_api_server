package kr.co.dbsg.api.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "jwt")
@ConfigurationPropertiesBinding
public record JwtProperty(
    String secret, String issuer, long expiration
) {
}

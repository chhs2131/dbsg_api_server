package kr.co.dbsg.api.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "api.naver")
@ConfigurationPropertiesBinding
public record NaverApiProperty(
        String baseUrl, String clientId, String clientSecret
) {
}

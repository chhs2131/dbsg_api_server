package kr.co.dbsg.api.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "api.kakao")
@ConfigurationPropertiesBinding
public record KakaoApiProperty(
    String baseUrl, String restApiKey
) {
}

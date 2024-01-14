package kr.co.dbsg.api.global.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = "kr.co.dbsg.api.global.config.properties")
public class PropertyConfiguration {
}

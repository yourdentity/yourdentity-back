package com.yourdentity.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("test")
@PropertySources({
        @PropertySource("classpath:properties/env-test.properties"), // env-dev.properties 파일 소스 등록
})
public class TestPropertyConfig {
}

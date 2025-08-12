package com.yourdentity.yourdentity.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Profile("dev")
@PropertySources({
        @PropertySource("classpath:properties/env-dev.properties"), // env-dev.properties 파일 소스 등록
})
public class DevPropertyConfig {

}

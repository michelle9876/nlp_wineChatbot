package com.springboot.fp_ml_web.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "스웨거 테스트",
                description = "테스트",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SpringDocsConfig {

    @Bean
    public GroupedOpenApi dbOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("테스트 v1")
                .pathsToMatch(paths)
                .build();
    }
}
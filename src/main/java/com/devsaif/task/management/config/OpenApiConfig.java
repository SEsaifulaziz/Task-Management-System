package com.devsaif.task.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskManagementOpnApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .description("REST API for managing tasks")
                        .version("1.0.0"));
    }
}

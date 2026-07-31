package com.smartexpensetracker.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseTrackerOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Smart Expense Tracker API")
                .version("1.0.0")
                .description("Manage personal expenses, search activity, and review monthly spending summaries."));
    }
}

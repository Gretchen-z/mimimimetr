package ru.gretchen.mimimimetr.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Mimimimetr service",
        version = "1.0.0",
        description = "Mimimimetr"))
public class SwaggerConfiguration {
}

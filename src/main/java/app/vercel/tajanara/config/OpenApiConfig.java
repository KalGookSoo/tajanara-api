package app.vercel.tajanara.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${spring.application.name}") String applicationName,
            @Value("${spring.application.version}") String applicationVersion,
            @Value("${server.servlet.context-path}") String contextPath
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " API Documentation")
                        .description("API documentation for " + applicationName)
                        .version(applicationVersion)
                        .contact(new Contact()
                                .name("Seesaw Team")
                                .email("ga.miro3721@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url(contextPath)
                                .description("Default Server URL")
                ))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT 토큰을 입력하세요. 'Bearer ' 접두사는 자동으로 추가됩니다.")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"));
    }

}

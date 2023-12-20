package de.bootcamp.springTodoExample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class SwaggerConfiguration {
    private final String projectDescription;
    private final String bugTicketUrl;

    public SwaggerConfiguration(@Value("${project.description}") String projectDescription,
            @Value("${project.ticket-url}") String bugTicketUrl) {
        this.projectDescription = projectDescription;
        this.bugTicketUrl = bugTicketUrl;

    }


    @Bean
    public OpenAPI apiDocket() {
        return new OpenAPI()
                .info(
                        new Info().title("ToDo List App")
                                .description(projectDescription)
                                .version("0.0.1")
                                .contact(new Contact().name("Bug Report").url(bugTicketUrl))
                );
    }

}

package project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//tells spring where to look
@Configuration
@EnableJpaRepositories(basePackages = "project.database")
public class DatabaseConfig {

}

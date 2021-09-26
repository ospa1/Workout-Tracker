package perscholas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//tells spring where to look
@Configuration
@EnableJpaRepositories(basePackages = "perscholas.database")
public class DatabaseConfig {

}

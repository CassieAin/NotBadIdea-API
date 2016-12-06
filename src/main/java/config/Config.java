package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.DBCredentials;
@Configuration
public class Config {

    @Bean
    public DBCredentials dbCredentials() {
        return new DBCredentials();
    }
}

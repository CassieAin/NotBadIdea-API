package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import service.DBConnection;
import service.DBCredentials;

/**
 * Created by user on 30.11.16.
 */
@Configuration
public class Config {

    @Bean
    public DBCredentials dbCredentials() {
        return new DBCredentials();
    }

}

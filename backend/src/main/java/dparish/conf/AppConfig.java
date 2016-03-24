package dparish.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dparish
 */
@Configuration
public class AppConfig {

    /**
     * @return the application wide object mapper
     */
    @Bean()
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

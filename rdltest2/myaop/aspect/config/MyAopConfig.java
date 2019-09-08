package aspect.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAopConfig {

	@Bean
    @ConfigurationProperties(prefix = "com.example.demo")
    public People people() {
        return new People();
    }
	
}

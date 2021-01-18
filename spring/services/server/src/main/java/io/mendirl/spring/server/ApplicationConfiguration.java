package io.mendirl.spring.server;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    ApplicationRunner runner(RetrieverService service) {
        return args -> service.showProperties();
    }
}

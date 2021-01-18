package io.mendirl.spring.server.persist;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration(proxyBeanMethods = false)
public class PersistConfiguration {

    @Bean
    ConnectionFactoryInitializer initializeDatabase(ConnectionFactory connectionFactory) {
        var inializer = new ConnectionFactoryInitializer();
        var populator = new ResourceDatabasePopulator(new ClassPathResource("sql/schema.sql"));
        inializer.setDatabasePopulator(populator);
        inializer.setConnectionFactory(connectionFactory);
        return inializer;
    }

}

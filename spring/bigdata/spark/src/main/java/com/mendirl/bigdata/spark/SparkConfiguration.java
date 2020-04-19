package com.mendirl.bigdata.spark;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Bean
    public SparkSession spark() {
        return SparkSession.builder()
            .appName("test")
            .master("local[*]")
            .getOrCreate();
    }

}

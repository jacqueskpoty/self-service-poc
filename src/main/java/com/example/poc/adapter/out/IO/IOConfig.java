package com.example.poc.adapter.out.IO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * This is configuration class. The configuration annotation is used by SpringBoot in order to treat his class
 * as a configuration class
 * This class makes a bean out of the third party S3 service
 */
@Configuration
public class IOConfig {

    /**
     * Makes the S3 Client available in our application context(Spring Boot terms)
     * @return an S3 Client Bean
     */
    @Bean
    public S3Client amazonS3() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}

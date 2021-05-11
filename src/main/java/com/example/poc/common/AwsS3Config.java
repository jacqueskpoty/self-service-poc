package com.example.poc.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {

    @Bean
    public S3Client amazonS3() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}

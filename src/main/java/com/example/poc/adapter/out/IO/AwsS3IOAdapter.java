package com.example.poc.adapter.out.IO;

import com.example.poc.application.port.out.IO.IOServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AwsS3IOAdapter implements IOServicePort {

    private final S3Client s3;

    //TODO to be moved into a configuration file
    private final String BUCKET_NAME = "pebblepost-dev-dashboard-uploads";

    @Override
    public void upload(MultipartFile file, String key) {
        try {
            s3.putObject(
                    PutObjectRequest
                            .builder()
                            .bucket(BUCKET_NAME)
                            .key(key)
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (Exception e) {
            throw new RuntimeException("S3 upload failed to upload");
        }
    }

    @Override
    public byte[] download(String key) throws IOException {
        return s3.getObject(GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build()).readAllBytes();
    }
}

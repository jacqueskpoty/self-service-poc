package com.example.poc.multiversion.v1.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3;
    private final String BUCKET_NAME = "pebblepost-dev-dashboard-uploads";

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

    public ResponseInputStream<GetObjectResponse> download(String key) {
        return s3.getObject(GetObjectRequest.builder()
            .bucket(BUCKET_NAME)
            .key(key)
            .build());
    }
}

package com.example.poc.application.port.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.InputStream;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AssetFileDto {
    private InputStream inputStream;
    private String name;
    private Long size;
    private byte[] fileBytes;
}

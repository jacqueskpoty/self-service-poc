package com.example.poc.adapter.in.web;

import com.example.poc.application.port.dto.AssetDto;
import com.example.poc.application.port.dto.AssetFileDto;
import com.example.poc.application.port.in.AssetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class AssetController {

    private final AssetUseCase assetUseCase;

    @Async
    @PostMapping("/account/{accountId}/upload/{assetType}")
    public ResponseEntity createAssetWithFile(
            @RequestPart("file") MultipartFile file,
            @PathVariable("accountId") String accountId,
            @PathVariable("assetType") String assetType
    ) throws IOException {
        AssetFileDto assetFileDto = AssetFileDto.builder()
                                    .name(file.getName())
                                    .inputStream(file.getInputStream())
                                    .size(file.getSize())
                                    .build();
        AssetDto assetDto = assetUseCase.addAssetWithFile(assetFileDto,accountId,assetType);
        return new ResponseEntity(assetDto, HttpStatus.OK);
    }


    @Async
    @GetMapping("/account/{accountId}/download/{assetId}")
    public ResponseEntity downloadAssetFile(
            @PathVariable("accountId") String accountId,
            @PathVariable("assetId") String assetId
    ){
        AssetFileDto assetFileResponse = assetUseCase.downloadAssetFile(accountId,assetId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+assetFileResponse.getName()+"\"")
                .body(assetFileResponse.getFileBytes());
    }
}

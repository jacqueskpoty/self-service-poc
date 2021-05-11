package com.example.poc.adapter.in.web.controller;

import com.example.poc.adapter.in.web.dto.AssetDTO;
import com.example.poc.adapter.in.web.mapper.AssetDTOMapper;
import com.example.poc.application.port.in.web.response.AssetFileResponse;
import com.example.poc.application.port.in.web.usecase.AssetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class AssetController {

    private final AssetUseCase assetUseCase;
    private final AssetDTOMapper assetDTOMapper;

    @Async
    @PostMapping("/account/{accountId}/upload/{assetType}")
    public AssetDTO createAssetWithFile(
            @RequestPart("file") MultipartFile file,
            @PathVariable("accountId") String accountId,
            @PathVariable("assetType") String assetType
    ) {
        return assetDTOMapper.toDto(
                assetUseCase.addAssetWithFile(file,accountId,assetType)
        );
    }


    @Async
    @GetMapping("/account/{accountId}/download/{assetId}")
    public ResponseEntity downloadAssetFile(
            @PathVariable("accountId") String accountId,
            @PathVariable("assetId") String assetId
    ){
        AssetFileResponse assetFileResponse = assetUseCase.downloadAssetFile(accountId,assetId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+assetFileResponse.getName()+"\"")
                .body(assetFileResponse.getFileBytes());
    }
}

package com.example.poc.adapter.in.web.controller;

import com.example.poc.adapter.in.web.dto.AssetDTO;
import com.example.poc.adapter.in.web.mapper.AccountDTOMapper;
import com.example.poc.adapter.in.web.mapper.AssetDTOMapper;
import com.example.poc.application.port.in.web.response.AssetFileResponse;
import com.example.poc.application.port.in.web.usecase.AssetUseCase;
import com.example.poc.domain.Asset;
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
    private final AssetDTOMapper assetDTOMapper = AssetDTOMapper.INSTANCE;

    @Async
    @PostMapping("/account/{accountId}/upload/{assetType}")
    public ResponseEntity createAssetWithFile(
            @RequestPart("file") MultipartFile file,
            @PathVariable("accountId") String accountId,
            @PathVariable("assetType") String assetType
    ){
        Asset asset = assetUseCase.addAssetWithFile(file,accountId,assetType);
        return new ResponseEntity(assetDTOMapper.toDto(asset), HttpStatus.OK);
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

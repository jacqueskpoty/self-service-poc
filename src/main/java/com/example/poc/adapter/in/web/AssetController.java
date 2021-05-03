package com.example.poc.adapter.in.web;

import com.example.poc.application.port.in.web.asset.AssetDTO;
import com.example.poc.application.port.in.web.asset.AssetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class is a RestController using Lombok annotation to avoid boiler plate code
 * Hover the mouse on each of the annotation to see what they do
 * Controller used to sent requests related to assets
 * It takes HTTP requests and forward them to the application module
 * It's an IN ADAPTER
 */
@Slf4j
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class AssetController {

    /**
     * Initializing the application's IN PORT so as to consume service from the application
     * We communicate with the application through this port
     * Particularly requests related to a Asset(Business term)
     * */
    private final AssetUseCase assetUseCase;

    /**
     * Post Method used to create an asset and upload a file which contains the data related to the asset
     * This happens Asynchronously
     * @param file the asset file
     * @param accountId the account the asset belongs to
     * @param assetType the asset type because there are different types of assets
     * @return an assetDTO to the client
     */
    @Async
    @PostMapping("/account/{accountId}/upload/{assetType}")
    public AssetDTO createAssetWithFile(
            @RequestPart("file") MultipartFile file,
            @PathVariable("accountId") String accountId,
            @PathVariable("assetType") String assetType
    ) {
        return assetUseCase.addAssetWithFile(file,accountId,assetType);
    }

    /**
     * This is a GET request used to download a file related to an asset for a particular account
     * @param accountId the account the asset belongs to
     * @param assetId the asset targeted's ID
     * @return Response Entity which's header contains the file downloaded and information related to the file
     */
    @Async
    @GetMapping("/account/{accountId}/download/{assetId}")
    public ResponseEntity downloadAssetFile(
            @PathVariable("accountId") String accountId,
            @PathVariable("assetId") String assetId
    ){
        var assetDTO = assetUseCase.getAsset(accountId, assetId);
        var fileBytes = assetUseCase.downloadAssetFile(assetDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+assetDTO.getName()+"\"")
                .body(fileBytes);
    }
}

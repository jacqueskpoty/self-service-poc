package com.example.poc.multiversion;

import com.example.poc.multiversion.v1.account.AccountDTO;
import com.example.poc.multiversion.v1.asset.Asset;
import com.example.poc.multiversion.v1.manager.AssetType;
import com.example.poc.multiversion.v1.flight.Flight;
import com.example.poc.multiversion.v1.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "api/{version}")
@RequiredArgsConstructor
public class AccountController {

    private final Map<String, AccountService> serviceMap;
    private final S3Service s3Service;

    @PostMapping("/account")
    public ResponseEntity create(
        @PathVariable("version") String version,
        @RequestBody AccountDTO account
    ) {
        return new ResponseEntity(serviceMap.get(version).create(account), HttpStatus.OK);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity getById(
        @PathVariable("version") String version,
        @PathVariable("accountId") String accountId
    ) {
        return new ResponseEntity(serviceMap.get(version).getById(accountId), HttpStatus.OK);
    }

    // @TODO: add FlightDTO abstraction l8ter
    @PostMapping("/account/{id}/flight")
    public ResponseEntity createFlight(
        @PathVariable("version") String version,
        @PathVariable("id") String id,
        @RequestBody Flight flight
    ) {
        return new ResponseEntity(serviceMap.get(version).addFlight(id, flight), HttpStatus.CREATED);
    }

    @Async
    @PostMapping("/account/{accountId}/upload/{assetType}")
    public Asset uploadFile(
        @RequestPart("file") MultipartFile file,
        @PathVariable("accountId") String accountId,
        @PathVariable("assetType") AssetType asset,
        @PathVariable("version") String version
    ) {
        return asset.manager()
            .manage(file)
            .root(accountId)
            .validate()
            .upload(s3Service::upload)
            .consume(serviceMap.get(version)::addAsset);
    }

    @Async
    @GetMapping("/account/{accountId}/download/{assetId}")
    public ResponseEntity download(
        @PathVariable("accountId") String accountId,
        @PathVariable("version") String version,
        @PathVariable("assetId") String assetId
    ) throws IOException {
        var asset = serviceMap.get(version).getAsset(accountId, assetId);
        var file = s3Service.download(asset.getLocation());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+asset.getName()+"\"")
            .body(file.readAllBytes());
    }

    @GetMapping("/account/{accountId}/{assetType}")
    public List<Asset> getZipBanks(
        @PathVariable("accountId") String accountId,
        @PathVariable("version") String version,
        @PathVariable("assetType") AssetType assetType
    ) {
        return serviceMap.get(version).getAssets(accountId, assetType);
    }
}
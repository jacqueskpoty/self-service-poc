package com.example.poc.application.service;

import com.example.poc.application.port.in.web.account.AccountDTOMapper;
import com.example.poc.application.port.in.web.asset.AssetDTO;
import com.example.poc.application.port.in.web.asset.AssetDTOMapper;
import com.example.poc.application.port.in.web.asset.AssetUseCase;
import com.example.poc.application.port.out.IO.IOServicePort;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.domain.Account;
import com.example.poc.domain.Asset;
import com.example.poc.domain.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Asset Service implementation, package private do not need to be directly accessed by any class
 * This class is part of the core of the application
 * It takes request coming through the PORT IN and forwards them to the PORT OUT
 * This class deals with use cases only related to the ASSET Domain
 * Uses Lombok Annotation
 */
@Service
@RequiredArgsConstructor
class AssetService implements AssetUseCase {


    private final AccountPort accountPort;

    private final IOServicePort ioServicePort;

    private final AssetDTOMapper assetDTOMapper;

    /**
     * Takes a create Asset from WEB IN and forwards it to the persistence PORT OUT for processing
     * @param id the account ID
     * @param assetDTO the assetDTO object
     * @return AssetDTO object
     */
    @Override
    public AssetDTO addAsset(String id, AssetDTO assetDTO) {
        return accountPort.getAccountById(id)
                .map(account -> account.appendAsset(assetDTO.getAsset()))
                .map(accountPort::saveAccount)
                .map(Account::getLatestAsset)
                .map(assetDTOMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Takes an asset creation request from the web but this time with a file that needs to be uploaded
     * We have to first create an asset and validate the asset,
     * forward the upload request to the IO service port and then,
     * sent a create Asset request to the persistence OUT PORT
     * @param file that client wants to upload for this asset
     * @param accountId of the account the asset belongs to
     * @param assetType the type of asset
     * @return AssetDTO object
     */
    @Override
    public AssetDTO addAssetWithFile(MultipartFile file, String accountId, String assetType) {
        try {
            Asset asset = AssetType.parse(assetType).getAsset().createAsset(file,accountId,assetType);
            ioServicePort.upload(file,asset.getLocation());
            return addAsset(accountId,assetDTOMapper.toDto(asset));
        } catch (Asset.InvalidAssetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Takes a get asset request from the WEB IN PORT and forwards it to the WEB OUT PORT for processing
     * @param accountId of the account the asset belongs to
     * @param assetId of the asset we want to get
     * @return AssetDAO Object
     */
    @Override
    public AssetDTO getAsset(String accountId, String assetId) {
        return accountPort.getAccountById(accountId)
                .map(Account.getAsset(assetId))
                .map(assetDTOMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Takes a get assets request by type, loads all the assets from the Persistence PORT OUT
     * filters them based on the type and returns them
     * @param accountId of the account the asset belongs to
     * @param type of the assets we want to get
     * @return a list of AssestDTO
     */
    @Override
    public List<AssetDTO> getAssets(String accountId, String type) {
        return accountPort.getAccountById(accountId)
                .map(Account::getAssets)
                .map(assets -> assets
                        .stream()
                        .filter(asset -> asset.getType().getAssetType().equalsIgnoreCase(type))
                        .map(assetDTOMapper::toDto)
                        .collect(Collectors.toList())
                ).orElseThrow(RuntimeException::new);
    }

    /**
     * Takes an asset file download request from the WEB PORT IN and forwards it to the
     * IO Service Port OUT which deals with file transfer
     * @param assetDTO the assetDTO which' file we want to download
     * @return an Array of bytes that represents the downloaded file
     */
    @Override
    public byte[] downloadAssetFile(AssetDTO assetDTO) {
        try {
            return ioServicePort.download(assetDTO.getLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

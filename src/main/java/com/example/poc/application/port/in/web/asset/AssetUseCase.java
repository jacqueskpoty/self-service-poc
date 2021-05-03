package com.example.poc.application.port.in.web.asset;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * UseCase or Service interface.
 * Represents the Web port through which the web adapter communicate with the web adapter
 */
public interface AssetUseCase {

    /**
     * Create a new Asset without uploading a file
     * @param id the account ID
     * @param assetDTO the assetDTO object
     * @return an AssetDTO
     */
    AssetDTO addAsset(String id, AssetDTO assetDTO);

    /**
     * Create an asset with a file to be uploaded
     * @param file that client wants to upload for this asset
     * @param accountId of the account the asset belongs to
     * @param assetType the type of asset
     * @return an AssetDTO to the client
     */
    AssetDTO addAssetWithFile(MultipartFile file, String accountId, String assetType);

    /**
     * Get an Asset
     * @param accountId of the account the asset belongs to
     * @param assetId of the asset we want to get
     * @return an AssetDTO
     */
    AssetDTO getAsset(String accountId, String assetId);

    /**
     * Get List of Assets
     * @param accountId of the account the asset belongs to
     * @param type of the assets we want to get
     * @return List of Assets
     */
    List<AssetDTO> getAssets(String accountId, String type);

    /**
     * Download an asset file
     * @param assetDTO the assetDTO which' file we want to download
     * @return a byteArray representing the file
     */
    byte[] downloadAssetFile(AssetDTO assetDTO);
}

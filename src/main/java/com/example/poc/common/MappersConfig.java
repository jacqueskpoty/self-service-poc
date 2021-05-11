package com.example.poc.common;

import com.example.poc.adapter.out.persistence.mapper.AccountDocumentMapper;
import com.example.poc.adapter.out.persistence.mapper.AssetDocumentMapper;
import com.example.poc.adapter.out.persistence.mapper.FlightDocumentMapper;
import com.example.poc.adapter.out.persistence.mapper.FlightSetDocumentMapper;
import com.example.poc.adapter.in.web.mapper.AccountDTOMapper;
import com.example.poc.adapter.in.web.mapper.AssetDTOMapper;
import com.example.poc.adapter.in.web.mapper.FlightDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

    @Bean
    public AccountDocumentMapper accountDocumentMapper(){
        return AccountDocumentMapper.INSTANCE;
    }

    @Bean
    public AssetDocumentMapper assetDocumentMapper(){
        return AssetDocumentMapper.INSTANCE;
    }

    @Bean
    public FlightDocumentMapper flightDocumentMapper(){
        return FlightDocumentMapper.INSTANCE;
    }

    @Bean
    public FlightSetDocumentMapper flightSetDocumentMapper(){
        return FlightSetDocumentMapper.INSTANCE;
    }

    @Bean
    public AccountDTOMapper accountDTOMapper(){
        return AccountDTOMapper.INSTANCE;
    }

    @Bean
    public AssetDTOMapper assetDTOMapper(){
        return AssetDTOMapper.INSTANCE;
    }

    @Bean
    public FlightDTOMapper flightDTOMapper(){
        return FlightDTOMapper.INSTANCE;
    }

}

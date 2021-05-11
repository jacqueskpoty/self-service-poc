package com.example.poc.common;

import com.example.poc.application.port.out.IO.IOServicePort;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.application.service.DefaultAccountUseCase;
import com.example.poc.application.service.DefaultAssetUseCase;
import com.example.poc.application.service.DefaultFlightUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public DefaultAccountUseCase accountService(AccountPort accountPort){
        return new DefaultAccountUseCase(accountPort);
    }

    @Bean
    public DefaultAssetUseCase assetService(AccountPort accountPort, IOServicePort ioServicePort){
        return new DefaultAssetUseCase(accountPort,ioServicePort);
    }

    @Bean
    public DefaultFlightUseCase flightService(AccountPort accountPort){
        return new DefaultFlightUseCase(accountPort);
    }

}

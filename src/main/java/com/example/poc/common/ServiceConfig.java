package com.example.poc.common;

import com.example.poc.application.port.out.IOServicePort;
import com.example.poc.application.port.out.AccountPort;
import com.example.poc.application.service.DefaultAccountUseCase;
import com.example.poc.application.service.DefaultAssetUseCase;
import com.example.poc.application.service.DefaultFlightUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public DefaultAccountUseCase defaultAccountUseCase(AccountPort accountPort){
        return new DefaultAccountUseCase(accountPort);
    }

    @Bean
    public DefaultAssetUseCase defaultAssetUseCase(AccountPort accountPort, IOServicePort ioServicePort){
        return new DefaultAssetUseCase(accountPort,ioServicePort);
    }

    @Bean
    public DefaultFlightUseCase defaultFlightUseCase(AccountPort accountPort){
        return new DefaultFlightUseCase(accountPort);
    }

}

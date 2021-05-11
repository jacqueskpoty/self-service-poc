package com.example.poc.common;

import com.example.poc.application.port.out.IO.IOServicePort;
import com.example.poc.application.port.out.persistence.AccountPort;
import com.example.poc.application.service.AccountService;
import com.example.poc.application.service.AssetService;
import com.example.poc.application.service.FlightService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AccountService accountService(AccountPort accountPort){
        return new AccountService(accountPort);
    }

    @Bean
    public AssetService assetService(AccountPort accountPort,IOServicePort ioServicePort){
        return new AssetService(accountPort,ioServicePort);
    }

    @Bean
    public FlightService flightService(AccountPort accountPort){
        return new FlightService(accountPort);
    }

}

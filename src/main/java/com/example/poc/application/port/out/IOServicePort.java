package com.example.poc.application.port.out;


import com.example.poc.application.port.dto.AssetFileDto;

import java.io.IOException;


public interface IOServicePort {

     void upload(AssetFileDto fileDto, String path);

     byte[] download(String path) throws IOException;
}

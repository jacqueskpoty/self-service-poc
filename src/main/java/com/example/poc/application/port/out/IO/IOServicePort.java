package com.example.poc.application.port.out.IO;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IOServicePort {

     void upload(MultipartFile file, String path);

     byte[] download(String path) throws IOException;
}

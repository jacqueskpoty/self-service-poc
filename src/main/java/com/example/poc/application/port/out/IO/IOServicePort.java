package com.example.poc.application.port.out.IO;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * This interface is a port the application layer uses to communicate with whatever IO Service we are using
 * Find the implementation in the adapter out
 */
public interface IOServicePort {
     /**
      * upload a file
      * @param file to be uploaded
      * @param path path to the file in the file system
      */
     void upload(MultipartFile file, String path);

     /**
      * Download a file
      * @param path path to the file in the file system
      * @return Bytes Array representing the file downloaded
      * @throws IOException
      */
     byte[] download(String path) throws IOException;
}

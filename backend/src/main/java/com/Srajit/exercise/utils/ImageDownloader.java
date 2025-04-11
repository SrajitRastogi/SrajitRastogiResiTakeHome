
package com.Srajit.exercise.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageDownloader {

    private static final String IMAGE_DIR = System.getProperty("user.home") + "/Downloads/mars-images/";
    // private static final String IMAGE_DIR = "/images/";



    public String downloadImage(String imageUrl, String baseFileName) {
        try {
            if (imageUrl.startsWith("http://mars.jpl.nasa.gov")) {
                imageUrl = imageUrl.replace("http://mars.jpl.nasa.gov", "https://mars.nasa.gov");
            }

            Path imageDir = Paths.get(IMAGE_DIR);
            Files.createDirectories(imageDir);

            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            String contentType = connection.getContentType();

           
            if (contentType == null || !contentType.startsWith("image")) {
                return "❌ Not an image, skipping download. Content-Type: " + contentType;
                
            }

            
            String extension = getExtensionFromContentType(contentType);

           
            Path imagePath = imageDir.resolve(baseFileName + extension);

            
            if (Files.exists(imagePath)) {
                String message = "Image already exists, skipping download: " + imagePath.toAbsolutePath();
                System.out.println("ℹ️ " + message);
                return message;
            }

         
            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(imagePath.toFile())) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }

                System.out.println("✅ Downloaded: " + imagePath.toAbsolutePath());
                return "Image downloaded successfully at: " + imagePath.toAbsolutePath();
            }

        } catch (IOException e) {
            String errorMessage = "❌ Failed to download image: " + imageUrl;
            System.err.println(errorMessage);
            e.printStackTrace();
            return errorMessage;
        }
    }

    
    private String getExtensionFromContentType(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            default:
                return ""; 
        }
    }
}

package com.selection.selectionMaster.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}") // Define the upload directory in application.properties
    private String uploadDir;

    @Override
    public String storeFile(MultipartFile file, String numeroIouBac, String fileType) {
        try {
            // Create the upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique file name using numeroIouBac, fileType, and the original file name
            String fileName = numeroIouBac + "_" + fileType + "_" + file.getOriginalFilename();

            // Save the file to the upload directory
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the file name (or full path, depending on your needs)
            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file: " + file.getOriginalFilename(), ex);
        }
    }
}
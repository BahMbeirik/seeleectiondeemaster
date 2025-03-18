package com.selection.selectionMaster.services;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  String storeFile(MultipartFile file, String numeroIouBac, String fileType);
}
package com.samtuga.rmufilemanagement.service;

import com.samtuga.rmufilemanagement.entity.Document;
import com.samtuga.rmufilemanagement.repository.StorageRepository;
import com.samtuga.rmufilemanagement.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public String uploadDocument(MultipartFile file) throws IOException {

        Document document = repository.save(Document.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .document(DocumentUtils.compressImage(file.getBytes())).build());
//        if (document != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<Document> dbImageData = repository.findByName(fileName);
        byte[] images=DocumentUtils.decompressImage(dbImageData.get().getDocument());
        return images;
    }
}

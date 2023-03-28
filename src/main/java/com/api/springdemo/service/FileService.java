package com.api.springdemo.service;

import com.api.springdemo.repository.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService implements IFileService{

    private IFileRepository iFileRepository;

    @Autowired
    public FileService(IFileRepository iFileRepository) {
        this.iFileRepository = iFileRepository;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return iFileRepository.store(file);
    }

    @Override
    public Resource downloadFile(String fileName) {
        return iFileRepository.load(fileName);
    }
}

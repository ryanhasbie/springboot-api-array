package com.api.springdemo.repository;

import com.api.springdemo.model.request.File;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface IFileRepository {
    String store(MultipartFile file);
    Resource load(String filename);
}

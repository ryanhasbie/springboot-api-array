package com.api.springdemo.model.request;

import org.springframework.web.multipart.MultipartFile;

public class File {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

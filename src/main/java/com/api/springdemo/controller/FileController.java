package com.api.springdemo.controller;

import com.api.springdemo.model.request.File;
import com.api.springdemo.model.response.SuccessResponse;
import com.api.springdemo.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    private IFileService iFileService;

    @Autowired
    public FileController(IFileService iFileService) {
        this.iFileService = iFileService;
    }

    @PostMapping
    public ResponseEntity upload(File file) {
        MultipartFile multipartFile = file.getFile();
        String filePath = iFileService.uploadFile(multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success uploaded!", filePath));
    }

    @GetMapping
    public ResponseEntity download(@RequestParam String fileName) {
        Resource file = iFileService.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity show(@PathVariable("filename") String filename) throws IOException {
        Resource file = iFileService.downloadFile(filename);
        byte[] imageBytes = StreamUtils.copyToByteArray(file.getInputStream());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }

}

package com.example.parser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@Slf4j
public class FileController {


    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("imageFile") MultipartFile file) throws IOException {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(filename);

            return ResponseEntity.ok(fileService.parseFile(file));
        } catch (Exception e) {
            log.error("Can`t be uploaded file: {}", e.getMessage());
            return ResponseEntity.ok(new FileParserResponse());
        }
    }
}

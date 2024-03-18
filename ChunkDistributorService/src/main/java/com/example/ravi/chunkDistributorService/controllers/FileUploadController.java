package com.example.ravi.chunkDistributorService.controllers;

import com.example.ravi.chunkDistributorService.models.FileUploadData;
import com.example.ravi.chunkDistributorService.services.FileUploadService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
    private static final String ROOT = "uploadFile";
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(ROOT)
    public ResponseEntity<String> uploadFile(@RequestBody FileUploadData fileUploadData){
        try {
            fileUploadService.processUploadedFile(fileUploadData);
            return new ResponseEntity<>("File is uploaded, processing will start happening soon",HttpStatus.OK);
        }catch (Exception e){
            LOG.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>("Failed to process the uploaded file",HttpStatus.EXPECTATION_FAILED);
        }
    }
}

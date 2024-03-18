package com.example.ravi.chunkDistributorService.services;

import com.example.ravi.chunkDistributorService.exceptions.InvalidUploadTypeException;
import com.example.ravi.chunkDistributorService.models.FileUploadData;

public interface FileUploadService {
    public void processUploadedFile(FileUploadData fileUploadData) throws InvalidUploadTypeException;
}

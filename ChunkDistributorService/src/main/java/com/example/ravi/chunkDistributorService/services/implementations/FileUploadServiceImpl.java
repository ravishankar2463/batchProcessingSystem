package com.example.ravi.chunkDistributorService.services.implementations;

import com.example.ravi.chunkDistributorService.exceptions.InvalidUploadTypeException;
import com.example.ravi.chunkDistributorService.models.FileUploadData;
import com.example.ravi.chunkDistributorService.services.ChunkDistributorService;
import com.example.ravi.chunkDistributorService.services.FileUploadService;
import com.example.ravi.chunkDistributorService.services.ThreadExecutorService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final ChunkDistributorService chunkDistributorService;
    private final ThreadExecutorService threadExecutorService;

    public FileUploadServiceImpl(ChunkDistributorService chunkDistributorService,ThreadExecutorService threadExecutorService) {
        this.chunkDistributorService = chunkDistributorService;
        this.threadExecutorService = threadExecutorService;
    }

    public void processUploadedFile(FileUploadData fileUploadData) throws InvalidUploadTypeException {
        if("local".equalsIgnoreCase(fileUploadData.getUploadType())){
            try(FileInputStream fileStream = new FileInputStream(new File(fileUploadData.getFileUri()))) {
                ExecutorService executorService = threadExecutorService.getExecutorService();

                executorService.submit(() -> {
                   chunkDistributorService.createChunks(fileStream,fileUploadData.getBatchSize());
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new InvalidUploadTypeException("Please Provide A Valid Upload Type");
        }
    }
}

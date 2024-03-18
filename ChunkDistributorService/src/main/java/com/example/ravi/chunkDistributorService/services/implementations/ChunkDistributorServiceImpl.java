package com.example.ravi.chunkDistributorService.services.implementations;

import com.example.ravi.chunkDistributorService.services.ChunkDistributorService;
import com.example.ravi.chunkDistributorService.services.ThreadExecutorService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class ChunkDistributorServiceImpl implements ChunkDistributorService {
    private final ThreadExecutorService threadExecutorService;

    public ChunkDistributorServiceImpl(ThreadExecutorService threadExecutorService) {
        this.threadExecutorService = threadExecutorService;
    }

    public void createChunks(FileInputStream inputStream, int batchSize){
        System.out.println("Distributed Chunks , Batch Size " + batchSize);
    }
}

package com.example.ravi.chunkDistributorService.services;

import java.io.FileInputStream;

public interface ChunkDistributorService {
    public void createChunks(FileInputStream inputStream, int batchSize);
}

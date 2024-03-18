package com.example.ravi.chunkDistributorService.services.implementations;

import com.example.ravi.chunkDistributorService.services.ThreadExecutorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ThreadExecutorServiceImpl implements ThreadExecutorService {
    private final ExecutorService executorService;
    public ThreadExecutorServiceImpl(@Value("${threads.pool.min.size:5}") int minimumThreadPoolSize, @Value("${threads.pool.max.size:10}") int maximumThreadPoolSize) {
        this.executorService = new ThreadPoolExecutor(minimumThreadPoolSize,
                maximumThreadPoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public ExecutorService getExecutorService(){
        return executorService;
    }
}

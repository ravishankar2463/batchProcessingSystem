package com.example.ravi.chunkDistributorService.models;

public class FileUploadData {
    private String fileName;
    private String uploadType;
    private int batchSize;
    private String fileUri;

    public FileUploadData(){}

    public FileUploadData(String fileName, String uploadType, int batchSize, String fileUri) {
        this.fileName = fileName;
        this.uploadType = uploadType;
        this.batchSize = batchSize;
        this.fileUri = fileUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }
}

package com.example.ravi.ProcessAndUserService.processes.models.builders;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProcessStepBuilder {
    private String name;
    private String description;
    private String successMessage;
    private String failureMessage;
    private Process process;

    public ProcessStepBuilder withName(String name){
        this.name = name;
        return this;
    }
    public ProcessStepBuilder withDescription(String description){
        this.description = description;
        return this;
    }
    public ProcessStepBuilder withSuccessMessage(String successMessage){
        this.successMessage = successMessage;
        return this;
    }
    public ProcessStepBuilder withFailureMessage(String failureMessage){
        this.failureMessage = failureMessage;
        return this;
    }
    public ProcessStepBuilder withProcess(Process process){
        this.process = process;
        return this;
    }

    public ProcessStep build(){
        return new ProcessStep(UUID.randomUUID().toString(),name,description,successMessage,failureMessage, LocalDateTime.now(),process);
    }
}

package com.example.ravi.ProcessAndUserService.processes.models.builders;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ProcessBuilder {
    private String name;
    private String routeName;
    private List<ProcessStep> processSteps;

    public ProcessBuilder withName(String processName){
        this.name = processName;
        return this;
    }

    public ProcessBuilder withRouteName(String routeName){
        this.routeName = routeName;
        return this;
    }

    public ProcessBuilder withProcessSteps(List<ProcessStep> processSteps){
        this.processSteps = processSteps;
        return this;
    }

    public Process build(){
        return new Process(UUID.randomUUID().toString(),name,routeName, LocalDateTime.now() ,processSteps);
    }
}

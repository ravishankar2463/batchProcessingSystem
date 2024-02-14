package com.example.ravi.ProcessAndUserService.processes.models.builders;

import com.example.ravi.ProcessAndUserService.processes.models.Process;

import java.util.UUID;

public class ProcessBuilder {
    private String name;
    private String routeName;

    public ProcessBuilder withName(String processName){
        this.name = processName;
        return this;
    }

    public ProcessBuilder withRouteName(String routeName){
        this.routeName = routeName;
        return this;
    }

    public Process build(){
        return new Process(UUID.randomUUID().toString(),name,routeName);
    }
}

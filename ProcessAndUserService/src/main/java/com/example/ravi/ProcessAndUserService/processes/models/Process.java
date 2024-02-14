package com.example.ravi.ProcessAndUserService.processes.models;

public class Process {
    private String id;
    private String name;
    private String routeName;

    public Process(){}

    public Process(String id, String name, String routeName) {
        this.id = id;
        this.name = name;
        this.routeName = routeName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRouteName() {
        return routeName;
    }
}

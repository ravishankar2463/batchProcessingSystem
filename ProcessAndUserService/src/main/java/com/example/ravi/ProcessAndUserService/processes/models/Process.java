package com.example.ravi.ProcessAndUserService.processes.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "processes")
public class Process {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String routeName;
    @OneToMany(mappedBy = "process")
    private List<ProcessStep> processSteps;

    public Process(){}

    public Process(String id, String name, String routeName) {
        this.id = id;
        this.name = name;
        this.routeName = routeName;
    }

    public Process(String id, String name, String routeName, List<ProcessStep> processSteps) {
        this(id,name,routeName);
        this.processSteps = processSteps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<ProcessStep> getProcessSteps() {
        return processSteps;
    }

    public void setProcessSteps(List<ProcessStep> processSteps) {
        this.processSteps = processSteps;
    }
}

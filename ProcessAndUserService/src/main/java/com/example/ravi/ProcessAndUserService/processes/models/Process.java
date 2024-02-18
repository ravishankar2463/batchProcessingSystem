package com.example.ravi.ProcessAndUserService.processes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "processes")
@Table(name = "processes")
public class Process extends RepresentationModel<Process> {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String routeName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "process")
    private List<ProcessStep> processSteps;

    public Process(){}

    public Process(String id, String name, String routeName){
        this.id = id;
        this.name = name;
        this.routeName = routeName;
    }

    public Process(String id, String name, String routeName, LocalDateTime createdDate) {
        this(id,name,routeName);
        this.createdDate = createdDate;
    }

    public Process(String id, String name, String routeName,LocalDateTime createdDate, List<ProcessStep> processSteps) {
        this(id,name,routeName,createdDate);
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<ProcessStep> getProcessSteps() {
        return processSteps;
    }

    public void setProcessSteps(List<ProcessStep> processSteps) {
        this.processSteps = processSteps;
    }
}

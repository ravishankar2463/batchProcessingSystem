package com.example.ravi.ProcessAndUserService.processes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Entity(name = "process_steps")
@Table(name = "process_steps")
public class ProcessStep extends RepresentationModel<ProcessStep> {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String successMessage;
    @Column
    private String failureMessage;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;

    public ProcessStep(){}

    public ProcessStep(String id, String name, String description, String successMessage, String failureMessage){
        this.id = id;
        this.name = name;
        this.description = description;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
    }

    public ProcessStep(String id, String name, String description, String successMessage, String failureMessage, LocalDateTime createdDate) {
        this(id,name,description,successMessage,failureMessage);
        this.createdDate = createdDate;
    }

    public ProcessStep(String id, String name, String description, String successMessage, String failureMessage,LocalDateTime createdDate, Process process) {
        this(id,name,description,successMessage,failureMessage,createdDate);
        this.process = process;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}

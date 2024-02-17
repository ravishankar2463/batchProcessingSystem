package com.example.ravi.ProcessAndUserService.processes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "process_steps")
public class ProcessStep {
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
    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;

    public ProcessStep(){}

    public ProcessStep(String id, String name, String description, String successMessage, String failureMessage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
    }

    public ProcessStep(String id, String name, String description, String successMessage, String failureMessage, Process process) {
        this(id,name,description,successMessage,failureMessage);
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

    public Process getProcess() {
        return process;
    }

    public void setProcessId(Process process) {
        this.process = process;
    }
}

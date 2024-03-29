package com.example.ravi.ProcessAndUserService.processes.service;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProcessService {
    Process registerProcess(Process process);
    void registerProcessSteps(List<ProcessStep> processStep,Process processId);
    List<Process> getAllProcesses();
    Process getProcessByIdentifier(String processIdentifier,String processIdentifierType);
    ProcessStep getProcessStepByIdentifier(String processIdentifier,String processIdentifierType,String processStepIdentifier,String processStepIdentifierType);
}


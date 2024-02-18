package com.example.ravi.ProcessAndUserService.processes.service.implementations;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import com.example.ravi.ProcessAndUserService.processes.models.builders.ProcessBuilder;
import com.example.ravi.ProcessAndUserService.processes.models.builders.ProcessStepBuilder;
import com.example.ravi.ProcessAndUserService.processes.repository.ProcessRepository;
import com.example.ravi.ProcessAndUserService.processes.repository.ProcessStepRepository;
import com.example.ravi.ProcessAndUserService.processes.service.ProcessService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessServiceImpl implements ProcessService {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessService.class);
    private final ProcessRepository processRepository;
    private final ProcessStepRepository processStepRepository;

    public ProcessServiceImpl(ProcessRepository processRepository,ProcessStepRepository processStepRepository){
        this.processRepository = processRepository;
        this.processStepRepository = processStepRepository;
    }

    @Override
    public Process registerProcess(Process process) {
        Optional<Process> processOptional = processRepository.findByName(process.getName());
        if(processOptional.isPresent()){
            LOG.info(String.format("Process with name %s already exists",process.getName()));
            return processOptional.get();
        }else {
            Process newProcess = new ProcessBuilder()
                                    .withName(process.getName())
                                    .withRouteName(process.getRouteName())
                                    .build();

            processRepository.save(newProcess);
            LOG.info(String.format("Process created : %s",process.getName()));
            return newProcess;
        }
    }
    @Override
    public void registerProcessSteps(List<ProcessStep> processSteps,Process process){
        processSteps.forEach(processStep -> {
            Optional<ProcessStep> processStepOptional = processStepRepository.findByNameAndProcessId(processStep.getName(),process.getId());
            if(processStepOptional.isPresent()){
                LOG.info(String.format("Process Step with name %s already exists for process %s",processStep.getName(),process.getId()));
            }else {
                ProcessStep newProcessStep = new ProcessStepBuilder()
                        .withName(processStep.getName())
                        .withDescription(processStep.getDescription())
                        .withSuccessMessage(processStep.getSuccessMessage())
                        .withFailureMessage(processStep.getFailureMessage())
                        .withProcess(process)
                        .build();

                processStepRepository.save(newProcessStep);
                LOG.debug(String.format("Process Step Added : %s in Process : %s",processStep.getName(),process.getName()));
            }
        });
    }

    @Override
    public List<Process> getAllProcesses(){
        List<Process> processList = new ArrayList<>();
        processRepository.findAll().iterator().forEachRemaining(processList::add);
        return processList;
    }

    @Override
    public Process getProcessByIdentifier(String processIdentifier, String processIdentifierType) {
        Optional<Process> processOptional;

        if("id".equalsIgnoreCase(processIdentifierType)) {
            processOptional = processRepository.findById(processIdentifier);
        }else if("name".equalsIgnoreCase(processIdentifierType)){
            processOptional = processRepository.findByName(processIdentifier);
        }else {
            throw new RuntimeException("");
        }

        return processOptional.orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public ProcessStep getProcessStepByIdentifier(String processIdentifier,
                                                  String processIdentifierType,
                                                  String processStepIdentifier,
                                                  String processStepIdentifierType) {
        if(!"id".equalsIgnoreCase(processIdentifierType)) {
            processIdentifier = getProcessByIdentifier(processIdentifier, processIdentifierType).getId();
        }

        Optional<ProcessStep> processStepOptional;
        if("id".equalsIgnoreCase(processStepIdentifierType)) {
            processStepOptional = processStepRepository.findByIdAndProcessId(processStepIdentifier,processIdentifier);
        }else if("name".equalsIgnoreCase(processStepIdentifierType)){
            processStepOptional = processStepRepository.findByNameAndProcessId(processStepIdentifier,processIdentifier);
        }else {
            throw new RuntimeException("");
        }

        return processStepOptional.orElseThrow(() -> new RuntimeException(""));
    }
}

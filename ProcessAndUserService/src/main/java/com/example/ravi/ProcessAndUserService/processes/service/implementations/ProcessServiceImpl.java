package com.example.ravi.ProcessAndUserService.processes.service.implementations;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import com.example.ravi.ProcessAndUserService.processes.models.builders.ProcessBuilder;
import com.example.ravi.ProcessAndUserService.processes.models.builders.ProcessStepBuilder;
import com.example.ravi.ProcessAndUserService.processes.repository.ProcessRepository;
import com.example.ravi.ProcessAndUserService.processes.repository.ProcessStepRepository;
import com.example.ravi.ProcessAndUserService.processes.service.ProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessServiceImpl implements ProcessService {
    private static final Log LOG = LogFactory.getLog(ProcessService.class);
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
            return newProcess;
        }
    }
    @Override
    public void registerProcessSteps(List<ProcessStep> processSteps,Process process){
//        processSteps.forEach(processStep -> {
//            Optional<ProcessStep> processStepOptional = processStepRepository.findByNameAndProcessId(processStep.getName(),process.getId());
//            if(processStepOptional.isPresent()){
//                LOG.info(String.format("Process Step with name %s already exists for process %s",processStep.getName(),process.getId()));
//            }else {
//                ProcessStep newProcessStep = new ProcessStepBuilder()
//                        .withName(processStep.getName())
//                        .withDescription(processStep.getDescription())
//                        .withSuccessMessage(processStep.getSuccessMessage())
//                        .withFailureMessage(processStep.getFailureMessage())
//                        .withProcess(process)
//                        .build();
//
//                processStepRepository.save(newProcessStep);
//            }
//        });
    }
}

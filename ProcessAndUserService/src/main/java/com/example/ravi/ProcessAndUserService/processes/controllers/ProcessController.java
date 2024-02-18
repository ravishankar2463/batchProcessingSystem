package com.example.ravi.ProcessAndUserService.processes.controllers;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import com.example.ravi.ProcessAndUserService.processes.service.ProcessService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProcessController {
    private static final String ROOT = "processes";
    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping(ROOT)
    public ResponseEntity<CollectionModel<Process>> getAllProcesses() {
        List<Process> processEntityModelsList = processService.getAllProcesses().stream().map(process -> {
            process.add(linkTo(methodOn(ProcessController.class).getProcessByIdentifier(process.getId(), "id")).withSelfRel());
            process.add(linkTo(methodOn(ProcessController.class).getProcessByIdentifier(process.getName(), "name")).withSelfRel());

            process.getProcessSteps().forEach(processStep -> {
                addSelfLinksToProcessStep(processStep, process.getId(), process.getName(), processStep.getId(), processStep.getName());
            });
            return process;
        }).toList();

        CollectionModel<Process> processCollectionModel = CollectionModel.of(processEntityModelsList);
        processCollectionModel.add(linkTo(methodOn(ProcessController.class).getAllProcesses()).withSelfRel());

        return new ResponseEntity<>(processCollectionModel, HttpStatus.OK);
    }

    @GetMapping(ROOT + "/{processIdentifier}")
    public ResponseEntity<Process> getProcessByIdentifier(@PathVariable("processIdentifier") String processIdentifier,
                                                          @RequestParam(value = "processIdentifierType", defaultValue = "id") String processIdentifierType) {
        Process process = processService.getProcessByIdentifier(processIdentifier, processIdentifierType);
        process.add(linkTo(methodOn(ProcessController.class).getProcessByIdentifier(process.getId(), "id")).withSelfRel());
        process.add(linkTo(methodOn(ProcessController.class).getProcessByIdentifier(process.getName(), "name")).withSelfRel());
        process.getProcessSteps().forEach(processStep -> {
            addSelfLinksToProcessStep(processStep, process.getId(), process.getName(), processStep.getId(), processStep.getName());
        });
        return new ResponseEntity<>(process, HttpStatus.OK);
    }

    @GetMapping(ROOT + "/{processIdentifier}" + "/processStep/{processStepIdentifier}")
    public ResponseEntity<ProcessStep> getProcessStepByIdentifier(@PathVariable("processIdentifier") String processIdentifier,
                                                                  @RequestParam(value = "processIdentifierType", defaultValue = "id") String processIdentifierType,
                                                                  @PathVariable("processStepIdentifier") String processStepIdentifier,
                                                                  @RequestParam(value = "processStepIdentifierType", defaultValue = "id") String processStepIdentifierType) {
        ProcessStep processStep = processService.getProcessStepByIdentifier(processIdentifier, processIdentifierType, processStepIdentifier, processStepIdentifierType);
        addSelfLinksToProcessStep(processStep, processStep.getProcess().getId(), processStep.getProcess().getName(), processStep.getId(), processStep.getName());
        return new ResponseEntity<>(processStep, HttpStatus.OK);
    }

    private void addSelfLinksToProcessStep(ProcessStep processStep, String processId, String processName, String processStepId, String processStepName) {
        addSelfLinkToProcessStep(processStep, processId, "id", processStepId, "id");
        addSelfLinkToProcessStep(processStep, processId, "id", processStepName, "name");
        addSelfLinkToProcessStep(processStep, processName, "name", processStepId, "id");
        addSelfLinkToProcessStep(processStep, processName, "name", processStepName, "name");
    }

    private void addSelfLinkToProcessStep(ProcessStep processStep,
                                          String processIdentifier,
                                          String processIdentifierType,
                                          String processStepIdentifier,
                                          String processStepIdentifierType) {
        processStep.add(linkTo(methodOn(ProcessController.class).getProcessStepByIdentifier(processIdentifier,
                processIdentifierType, processStepIdentifier, processStepIdentifierType)).withSelfRel());
    }

}

package com.example.ravi.ProcessAndUserService;

import com.example.ravi.ProcessAndUserService.processes.models.Process;
import com.example.ravi.ProcessAndUserService.processes.models.ProcessStep;
import com.example.ravi.ProcessAndUserService.processes.service.ProcessService;
import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessXmlElement;
import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import com.example.ravi.ProcessAndUserService.processes.xml.parsers.ProcessConfigXmlParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ApplicationLauncher implements CommandLineRunner {
    private static final Log LOG = LogFactory.getLog(ApplicationLauncher.class);
    private final String processConfigPath;
    private final ProcessService processService;

    public ApplicationLauncher(@Value("${process.config.path}") String processConfigPath,ProcessService processService){
        this.processConfigPath = processConfigPath;
        this.processService = processService;
    }

    public void launchApplication(){
        LOG.info("processConfigPath " + processConfigPath);

        Optional<ProcessesXmlElement> processesXmlElement =
                ProcessConfigXmlParser.parseDocument(processConfigPath);

        processesXmlElement.ifPresentOrElse((e) -> {
            e.getProcessesList().forEach(processXmlElement -> {
                        Process process = new Process("",processXmlElement.getName(),processXmlElement.getRoute().getName());
                        Process savedProcess = processService.registerProcess(process);

                        List<ProcessXmlElement.ProcessStepsXmlElement.ProcessStepXmlElement> xmlProcessStepsList = processXmlElement.getProcessStepsXmlElement()
                                                                                                                                    .getProcessStepsXmlElementList();

                        registerDefaultProcessSteps(savedProcess);
                        if(!CollectionUtils.isEmpty(xmlProcessStepsList)){
                            processService.registerProcessSteps(
                                    xmlProcessStepsList.stream()
                                            .map(xmlProcessStep -> new ProcessStep("",
                                                                    xmlProcessStep.getName(),
                                                                    xmlProcessStep.getDescription(),
                                                                    xmlProcessStep.getSuccessMessage(),
                                                                    xmlProcessStep.getFailureMessage())).toList(),
                                    savedProcess);
                        }
                }
            );
        },() -> {
            LOG.error("Was Not Able To Parse Process Config Xml");
        });
    }

    private void registerDefaultProcessSteps(Process savedProcess) {
        if(savedProcess.getProcessSteps() == null || (savedProcess.getProcessSteps() != null && savedProcess.getProcessSteps().size() < 2)){
            List<ProcessStep> processStepList = new ArrayList<>();

            processStepList.add(new ProcessStep("",
                                                "SavedToDatabase",
                                                "Record Has Been Saved To Database",
                                                "Saved To Database Success",
                                                "Saved To Database Failed"));

            processStepList.add(new ProcessStep("",
                                                    "RecordPickedByRouteForProcessing",
                                                    "Record Has Been Picked By Route Service To Process",
                                                    "Route Service Process Start Success",
                                                    "Route Service Process Start Fail"));

            processService.registerProcessSteps(processStepList,savedProcess);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        launchApplication();
    }
}

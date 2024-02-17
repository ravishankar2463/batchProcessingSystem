package com.example.ravi.ProcessAndUserService;

import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import com.example.ravi.ProcessAndUserService.processes.xml.parsers.ProcessConfigXmlParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationLauncher implements CommandLineRunner {
    private static final Log LOG = LogFactory.getLog(ApplicationLauncher.class);
    private final String processConfigPath;

    public ApplicationLauncher(@Value("${process.config.path}") String processConfigPath){
        this.processConfigPath = processConfigPath;
    }

    public void launchApplication(){
        LOG.info("processConfigPath " + processConfigPath);

        Optional<ProcessesXmlElement> processesXmlElement =
                ProcessConfigXmlParser.parseDocument(processConfigPath);

        processesXmlElement.ifPresentOrElse((e) -> {
            e.getProcessesList().forEach(
                    System.out::println
            );
        },() -> {
            LOG.error("Was Not Able To Parse Process Config Xml");
        });
    }

    @Override
    public void run(String... args) throws Exception {
        launchApplication();
    }
}

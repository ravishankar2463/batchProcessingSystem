package com.example.ravi.ProcessAndUserService;

import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import com.example.ravi.ProcessAndUserService.processes.xml.parsers.ProcessConfigXmlParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ProcessAndUserServiceApplication implements CommandLineRunner {
	private Log LOG = LogFactory.getLog(ProcessAndUserServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProcessAndUserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<ProcessesXmlElement> processesXmlElement = ProcessConfigXmlParser.parseDocument("batchProcessingSystem/ProcessAndUserService/src/main/resources/configs/processConfig.xml");

		processesXmlElement.ifPresentOrElse((x) -> {
			System.out.println(x.getProcessesList().get(0).getName());
			System.out.println(x.getProcessesList().get(0).getRoute().getName());
		},() -> {
			System.out.println("Was Not Able To Parse Process Config Xml");
		});
	}
}

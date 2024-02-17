package com.example.ravi.ProcessAndUserService.processes.xml.handlers;

import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
class ProcessConfigXmlHandlerTest {
    private SAXParser saxParser;
    private ProcessConfigXmlHandler xmlHandler;
    private String processConfigXmlPath;

    @BeforeEach
    public void setUp() throws ParserConfigurationException, SAXException, IOException {
        saxParser = SAXParserFactory.newInstance().newSAXParser();
        xmlHandler = new ProcessConfigXmlHandler();
        processConfigXmlPath = "src/test/resources/processConfig.xml";
    }

    @Test
    public void shouldReadProcessConfigXml() throws IOException, SAXException {
        saxParser.parse(processConfigXmlPath,xmlHandler);

        ProcessesXmlElement root = xmlHandler.getProcessesXmlElement();
        Assertions.assertEquals("SampleProcess",root.getProcessesList().get(0).getName());
        Assertions.assertEquals("SampleProcessRoute",root.getProcessesList().get(0).getRoute().getName());

        Assertions.assertEquals("SampleProcessStepName",root.getProcessesList().get(0).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getName());
        Assertions.assertEquals("SampleProcessStepDescription",root.getProcessesList().get(0).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getDescription());
        Assertions.assertEquals("SampleProcessStepSuccessMessage",root.getProcessesList().get(0).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getSuccessMessage());
        Assertions.assertEquals("SampleProcessStepFailureMessage",root.getProcessesList().get(0).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getFailureMessage());

        Assertions.assertEquals("SampleProcess2",root.getProcessesList().get(1).getName());
        Assertions.assertEquals("SampleProcess2Route",root.getProcessesList().get(1).getRoute().getName());

        Assertions.assertEquals("SampleProcess2StepName",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getName());
        Assertions.assertEquals("SampleProcess2StepDescription",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getDescription());
        Assertions.assertEquals("SampleProcess2StepSuccessMessage",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getSuccessMessage());
        Assertions.assertEquals("SampleProcess2StepFailureMessage",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(0).getFailureMessage());

        Assertions.assertEquals("SampleProcess2Step2Name",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(1).getName());
        Assertions.assertEquals("SampleProcess2Step2Description",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(1).getDescription());
        Assertions.assertEquals("SampleProcess2Step2SuccessMessage",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(1).getSuccessMessage());
        Assertions.assertEquals("SampleProcess2Step2FailureMessage",root.getProcessesList().get(1).getProcessStepsXmlElement().getProcessStepsXmlElementList().get(1).getFailureMessage());
    }

}
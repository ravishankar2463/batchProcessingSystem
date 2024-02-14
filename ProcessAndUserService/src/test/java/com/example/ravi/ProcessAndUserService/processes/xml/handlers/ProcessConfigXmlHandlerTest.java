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
    }

}
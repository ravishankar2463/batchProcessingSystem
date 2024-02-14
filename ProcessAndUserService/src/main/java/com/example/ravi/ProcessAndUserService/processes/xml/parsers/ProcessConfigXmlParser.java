package com.example.ravi.ProcessAndUserService.processes.xml.parsers;

import com.example.ravi.ProcessAndUserService.processes.xml.handlers.ProcessConfigXmlHandler;
import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Optional;

public class ProcessConfigXmlParser {
    private static final Log LOG = LogFactory.getLog(ProcessConfigXmlParser.class);
    private static final ProcessConfigXmlHandler xmlHandler = new ProcessConfigXmlHandler();

    public static Optional<ProcessesXmlElement> parseDocument(String xmlConfigPath) {
        try {
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(xmlConfigPath,xmlHandler);
            return Optional.of(xmlHandler.getProcessesXmlElement());
        }catch (Exception e){
            LOG.error(ExceptionUtils.getStackTrace(e));
            return Optional.empty();
        }
    }
}

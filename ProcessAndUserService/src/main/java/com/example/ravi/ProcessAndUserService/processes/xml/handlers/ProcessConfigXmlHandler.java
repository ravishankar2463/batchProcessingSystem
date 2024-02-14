package com.example.ravi.ProcessAndUserService.processes.xml.handlers;

import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessXmlElement;
import com.example.ravi.ProcessAndUserService.processes.xml.models.ProcessesXmlElement;
import org.springframework.util.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ProcessConfigXmlHandler extends DefaultHandler {
    private String currentValue;
    private static final String PROCESSES = "Processes";
    private static final String PROCESS = "Process";
    private static final String ROUTE = "Route";
    private static final String PROCESS_NAME = "Name";
    private static final String ROUTE_NAME = "RouteName";
    private ProcessesXmlElement processesXmlElement;

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        processesXmlElement = new ProcessesXmlElement();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case PROCESSES -> processesXmlElement.setProcessesList(new ArrayList<>());
            case PROCESS -> processesXmlElement.getProcessesList().add(new ProcessXmlElement());
            case ROUTE -> getLastAddedProcess().setRoute(new ProcessXmlElement.Route());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case PROCESS_NAME -> getLastAddedProcess().setName(StringUtils.trimAllWhitespace(currentValue));
            case ROUTE_NAME -> getLastAddedProcess().getRoute().setName(StringUtils.trimAllWhitespace(currentValue));
        }
    }

    private ProcessXmlElement getLastAddedProcess(){
        List<ProcessXmlElement> processXmlElementList = processesXmlElement.getProcessesList();
        return processXmlElementList.get(processXmlElementList.size()-1);
    }

    public ProcessesXmlElement getProcessesXmlElement(){
        return processesXmlElement;
    }
}

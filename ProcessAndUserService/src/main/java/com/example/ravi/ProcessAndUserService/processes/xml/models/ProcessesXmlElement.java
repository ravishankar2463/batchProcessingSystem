package com.example.ravi.ProcessAndUserService.processes.xml.models;

import java.util.List;

public class ProcessesXmlElement {
    private List<ProcessXmlElement> processesList;

    public ProcessesXmlElement(){}

    public ProcessesXmlElement(List<ProcessXmlElement> processesList) {
        this.processesList = processesList;
    }

    public List<ProcessXmlElement> getProcessesList() {
        return processesList;
    }

    public void setProcessesList(List<ProcessXmlElement> processesList) {
        this.processesList = processesList;
    }
}

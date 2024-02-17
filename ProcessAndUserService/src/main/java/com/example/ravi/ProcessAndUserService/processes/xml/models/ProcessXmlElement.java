package com.example.ravi.ProcessAndUserService.processes.xml.models;

import java.util.List;

public class ProcessXmlElement {
    private String name;
    private RouteXmlElement routeXmlElement;
    private ProcessStepsXmlElement processStepsXmlElement;

    public ProcessXmlElement(){}

    public ProcessXmlElement(String name, RouteXmlElement routeXmlElement,ProcessStepsXmlElement processStepsXmlElement) {
        this.name = name;
        this.routeXmlElement = routeXmlElement;
        this.processStepsXmlElement = processStepsXmlElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouteXmlElement getRoute() {
        return routeXmlElement;
    }

    public void setRoute(RouteXmlElement routeXmlElement) {
        this.routeXmlElement = routeXmlElement;
    }

    public RouteXmlElement getRouteXmlElement() {
        return routeXmlElement;
    }

    public void setRouteXmlElement(RouteXmlElement routeXmlElement) {
        this.routeXmlElement = routeXmlElement;
    }

    public ProcessStepsXmlElement getProcessStepsXmlElement() {
        return processStepsXmlElement;
    }

    public void setProcessStepsXmlElement(ProcessStepsXmlElement processStepsXmlElement) {
        this.processStepsXmlElement = processStepsXmlElement;
    }

    public static class RouteXmlElement {
        private String name;

        public RouteXmlElement(){}

        public RouteXmlElement(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProcessStepsXmlElement{
        private List<ProcessStepXmlElement> processStepsXmlElementList;
        public  ProcessStepsXmlElement(){}
        public  ProcessStepsXmlElement(List<ProcessStepXmlElement> processStepsXmlElementList){
            this.processStepsXmlElementList = processStepsXmlElementList;
        }

        public List<ProcessStepXmlElement> getProcessStepsXmlElementList() {
            return processStepsXmlElementList;
        }

        public void setProcessStepsXmlElementList(List<ProcessStepXmlElement> processStepsXmlElementList) {
            this.processStepsXmlElementList = processStepsXmlElementList;
        }

        public static class ProcessStepXmlElement{
            private String name;
            private String description;
            private String successMessage;
            private String failureMessage;

            public ProcessStepXmlElement(){}

            public ProcessStepXmlElement(String name, String description, String successMessage, String failureMessage) {
                this.name = name;
                this.description = description;
                this.successMessage = successMessage;
                this.failureMessage = failureMessage;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getSuccessMessage() {
                return successMessage;
            }

            public void setSuccessMessage(String successMessage) {
                this.successMessage = successMessage;
            }

            public String getFailureMessage() {
                return failureMessage;
            }

            public void setFailureMessage(String failureMessage) {
                this.failureMessage = failureMessage;
            }
        }
    }

}

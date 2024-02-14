package com.example.ravi.ProcessAndUserService.processes.xml.models;

public class ProcessXmlElement {
    private String name;
    private Route route;

    public ProcessXmlElement(){}

    public ProcessXmlElement(String name, Route route) {
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public static class Route{
        private String name;

        public Route(){}

        public Route(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

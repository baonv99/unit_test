package com.vnpay.springapigradle.entitites;

public class ElasticAgent {
    private String host;
    private String port;
    private String index;

    public ElasticAgent(String host, String port, String index) {
        this.host = host;
        this.port = port;
        this.index = index;
    }
    public void getInfo() {
        System.out.println(String.format("Host: %s, Port: %s, Index: %s", host, port, index));
    }
}


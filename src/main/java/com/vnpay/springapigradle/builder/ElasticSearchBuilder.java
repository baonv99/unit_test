package com.vnpay.springapigradle.builder;

import com.vnpay.springapigradle.entitites.ElasticAgent;

public class ElasticSearchBuilder {
    private String host;
    private String port;
    private String index;
    public ElasticSearchBuilder (String host, String port) {
        this.host = host;
        this.port = port;
    }
    public ElasticSearchBuilder setIndex(String index) {
        this.index = index;
        return this;
    }

    public ElasticAgent build() {
        ElasticAgent elasticAgent = new ElasticAgent(
                this.host, this.port,
                this.index);
        return elasticAgent;
    }
}

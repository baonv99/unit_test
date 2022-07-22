package com.vnpay.springapigradle.implementinterfacefactory;

import com.vnpay.springapigradle.interfacefactory.Table;

public class PlasticTable implements Table {
    @Override
    public void create() {
        System.out.println("Create Plastic Table");
    }
}

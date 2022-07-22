package com.vnpay.springapigradle.implementinterfacefactory;

import com.vnpay.springapigradle.interfacefactory.Table;

public class WoodTable implements Table {
    @Override
    public void create() {
        System.out.println("Create Wood Table");
    }
}

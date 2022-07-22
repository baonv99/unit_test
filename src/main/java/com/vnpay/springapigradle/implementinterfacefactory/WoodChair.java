package com.vnpay.springapigradle.implementinterfacefactory;

import com.vnpay.springapigradle.interfacefactory.Chair;

public class WoodChair implements Chair {
    @Override
    public void create() {
        System.out.println("Create Chair Wood!");
    }
}

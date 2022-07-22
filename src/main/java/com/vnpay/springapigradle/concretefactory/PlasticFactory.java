package com.vnpay.springapigradle.concretefactory;

import com.vnpay.springapigradle.abtractfactory.HomeAbstractFactory;
import com.vnpay.springapigradle.implementinterfacefactory.PlasticChair;
import com.vnpay.springapigradle.implementinterfacefactory.PlasticTable;
import com.vnpay.springapigradle.interfacefactory.Chair;
import com.vnpay.springapigradle.interfacefactory.Table;

public class PlasticFactory extends HomeAbstractFactory {
    @Override
    public Chair createChair() {
        return new PlasticChair();
    }

    @Override
    public Table createTable() {
        return new PlasticTable();
    }
}

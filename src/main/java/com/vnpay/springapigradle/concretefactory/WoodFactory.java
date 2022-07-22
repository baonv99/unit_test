package com.vnpay.springapigradle.concretefactory;

import com.vnpay.springapigradle.abtractfactory.HomeAbstractFactory;
import com.vnpay.springapigradle.implementinterfacefactory.WoodChair;
import com.vnpay.springapigradle.implementinterfacefactory.WoodTable;
import com.vnpay.springapigradle.interfacefactory.Chair;
import com.vnpay.springapigradle.interfacefactory.Table;

public class WoodFactory extends HomeAbstractFactory {
    @Override
    public Chair createChair() {
        return new WoodChair();
    }

    @Override
    public Table createTable() {
        return new WoodTable();
    }
}

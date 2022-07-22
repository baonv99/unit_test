package com.vnpay.springapigradle.abtractfactory;

import com.vnpay.springapigradle.interfacefactory.Chair;
import com.vnpay.springapigradle.interfacefactory.Table;

public abstract class HomeAbstractFactory {
    public abstract Chair createChair();
    public abstract Table createTable();
}

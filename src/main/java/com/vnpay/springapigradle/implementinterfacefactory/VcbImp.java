package com.vnpay.springapigradle.implementinterfacefactory;

import com.vnpay.springapigradle.interfacefactory.Bank;

public class VcbImp implements Bank {
    @Override
    public String getNameBank() {
        return "VCB";
    }
}

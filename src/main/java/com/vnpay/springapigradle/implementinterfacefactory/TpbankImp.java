package com.vnpay.springapigradle.implementinterfacefactory;

import com.vnpay.springapigradle.interfacefactory.Bank;

public class TpbankImp implements Bank {
    @Override
    public String getNameBank() {
        return "TPBANK";
    }
}

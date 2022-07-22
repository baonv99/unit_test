package com.vnpay.springapigradle.superfactory;

import com.vnpay.springapigradle.enumfactory.BankType;
import com.vnpay.springapigradle.implementinterfacefactory.TpbankImp;
import com.vnpay.springapigradle.implementinterfacefactory.VcbImp;
import com.vnpay.springapigradle.interfacefactory.Bank;

public class BankMainFactory {
    private BankMainFactory() {

    }
    public static Bank getBank(BankType materialType) {
        switch (materialType) {
            case VCB:
                return new VcbImp();
            case TPBANK:
                return new TpbankImp();
            default:
                throw new UnsupportedOperationException("This furniture is unsupported ");
        }
    }
}

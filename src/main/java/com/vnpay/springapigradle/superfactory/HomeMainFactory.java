package com.vnpay.springapigradle.superfactory;

import com.vnpay.springapigradle.abtractfactory.HomeAbstractFactory;
import com.vnpay.springapigradle.concretefactory.PlasticFactory;
import com.vnpay.springapigradle.concretefactory.WoodFactory;
import com.vnpay.springapigradle.enumfactory.MaterialType;

public class HomeMainFactory {
    private HomeMainFactory() {

    }
    public static HomeAbstractFactory getFactory(MaterialType materialType) {
        switch (materialType) {
            case PLASTIC:
                return new PlasticFactory();
            case WOOD:
                return new WoodFactory();
            default:
                throw new UnsupportedOperationException("This furniture is unsupported ");
        }
    }
}

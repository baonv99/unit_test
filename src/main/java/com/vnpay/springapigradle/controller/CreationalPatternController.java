package com.vnpay.springapigradle.controller;

import com.vnpay.springapigradle.abtractfactory.HomeAbstractFactory;
import com.vnpay.springapigradle.builder.ElasticSearchBuilder;
import com.vnpay.springapigradle.common.TimeSingleton;
import com.vnpay.springapigradle.entitites.ElasticAgent;
import com.vnpay.springapigradle.enumfactory.BankType;
import com.vnpay.springapigradle.enumfactory.MaterialType;
import com.vnpay.springapigradle.interfacefactory.Bank;
import com.vnpay.springapigradle.interfacefactory.Chair;
import com.vnpay.springapigradle.interfacefactory.Table;
import com.vnpay.springapigradle.superfactory.BankMainFactory;
import com.vnpay.springapigradle.superfactory.HomeMainFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreationalPatternController {
    @RequestMapping(value = "testsingleton", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public TimeSingleton testSingleTon() {
        TimeSingleton rspObj = TimeSingleton.getInstanceTime();
        try {
            rspObj.setTime("12h");
            rspObj.setMessage("Message");
            System.out.println("Create!!");
            return rspObj;
        } catch (Exception ex) {
            System.out.println("Message " + ex.getMessage());
            return rspObj;
        }
    }
    @RequestMapping(value = "testabstractfactory", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public TimeSingleton testAbstractFactory() {
        // AbstractFactory: Khai báo dạng interface hoặc abstract class chứa các phương thức để tạo ra các đối tượng abstract.
        // ConcreteFactory: Xây dựng, cài đặt các phương thức tạo các đối tượng cụ thể.
        // AbstractProduct: Khai báo dạng interface hoặc abstract class để định nghĩa đối tượng abstract.
        // Product: Cài đặt của các đối tượng cụ thể, cài đặt các phương thức được quy định tại AbstractProduct.
        // Client: là đối tượng sử dụng AbstractFactory và các AbstractProduct.\
        TimeSingleton rspObj = TimeSingleton.getInstanceTime();
        try {
            HomeAbstractFactory factory = HomeMainFactory.getFactory(MaterialType.WOOD);
            Chair chair = factory.createChair();
            chair.create(); // Create plastic chair
            Table table = factory.createTable();
            table.create(); // Create plastic table
            rspObj.setTime("12h");
            rspObj.setMessage("Message");
            System.out.println("Create!!");
            return rspObj;
        } catch (Exception ex) {
            System.out.println("Message " + ex.getMessage());
            return rspObj;
        }
    }
    @RequestMapping(value = "testfactorymethod", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public TimeSingleton testFactoryMethod() {
        TimeSingleton rspObj = TimeSingleton.getInstanceTime();
        try {
            Bank bank = BankMainFactory.getBank(BankType.TPBANK);
            System.out.println(bank.getNameBank());
            rspObj.setTime("12h");
            rspObj.setMessage("Message");
            System.out.println("Create!!");
            return rspObj;
        } catch (Exception ex) {
            System.out.println("Message " + ex.getMessage());
            return rspObj;
        }
    }
    @RequestMapping(value = "testbuilder", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public TimeSingleton testBuilder() {
        TimeSingleton rspObj = TimeSingleton.getInstanceTime();
        try {
            ElasticAgent elasticAgent = new ElasticSearchBuilder("127.0.0.1","9200").setIndex("count").build();
            elasticAgent.getInfo();
            rspObj.setTime("12h");
            rspObj.setMessage("Message");
            System.out.println("Create!!");
            return rspObj;
        } catch (Exception ex) {
            System.out.println("Message " + ex.getMessage());
            return rspObj;
        }
    }
}

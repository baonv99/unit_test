package com.vnpay.springapigradle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vnpay.springapigradle.AppConfig;
import com.vnpay.springapigradle.dao.TxnDao;
import com.vnpay.springapigradle.entitites.ReqAllTxn;
import com.vnpay.springapigradle.entitites.ReqTxn;
import com.vnpay.springapigradle.entitites.RspObj;
import com.vnpay.springapigradle.entitites.Txn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import static com.vnpay.springapigradle.common.Utils.*;

@Service
public class TxnService {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    AppConfig appConfig;
    @Autowired
    TxnDao txnDao;
    final Logger log = LoggerFactory.getLogger("request_api");
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public RspObj createTxn(ReqTxn req) {
        try {
            Txn txn = new Txn();
            txn.setTxn_id(req.getTxn_id());
            txn.setTxn_date(new Timestamp(System.currentTimeMillis()));
            txn.setCreated_by(req.getCreated_by());
            txn.setName(req.getName());
            txn.setEmail(req.getEmail());
            txn.setPhone_number(req.getPhone_number());
            txn.setGender(req.getGender());
            txn.setCode(req.getCode());
            txn.setMessage(req.getMessage());
            boolean rs = txnDao.createTxn(txn);
            if (rs) {
                return setResponseCustom(CODE_SUCCESS, "Thêm mới thành công!");
            } else {
                return setResponseCustom(CODE_EXCEPTION, "Có lỗi trong quá trình thêm mới!");
            }
        } catch (Exception ex) {
            return setResponseCustom(CODE_EXCEPTION, "API ERROR!");
        }
    }

    public RspObj updateTxn(ReqTxn req) {
        RspObj rspObj = new RspObj();
        try {
            Txn txn = new Txn();
            txn.setTxn_id(req.getTxn_id());
            txn.setUpdated_by(req.getUpdated_by());
            txn.setUpdated_date(req.getUpdated_date());
            txn.setName(req.getName());
            txn.setEmail(req.getEmail());
            txn.setGender(req.getGender());
            txn.setPhone_number(req.getPhone_number());
            txn.setCode(req.getCode());
            txn.setMessage(req.getMessage());
            boolean rs = txnDao.updateTxn(txn);
            if (rs) {
                return setResponseCustom(CODE_SUCCESS, "Cập nhật thành công!");
            } else {
                return setResponseCustom(CODE_EXCEPTION, "Có lỗi xảy ra trong quá trình cập nhật!");
            }
        } catch (Exception ex) {
            return setResponseCustom(CODE_EXCEPTION, "API ERROR!");
        }
    }
    public RspObj getTxnID(long txnId) {
        try {
            Txn txn = new Txn();
            txn = txnDao.getTxnId(txnId);
            if (txn != null) {
                String jsonResponseTxn = ow.writeValueAsString(txn);
                return setResponseCustom(CODE_SUCCESS, jsonResponseTxn);
            } else {
                return setResponseCustom(CODE_EXCEPTION, "NOT FOULD TXN ID");
            }
        } catch (Exception ex) {
            return setResponseCustom(CODE_EXCEPTION, "API ERROR!");
        }
    }
    public RspObj getAllTxn(ReqAllTxn req) {
        RspObj rspObj = new RspObj();
        try {
            ArrayList<Txn> listTxn;
            if (req.getPage_no() <= 0 || req.getPage_size() <= 0) {
                return setResponseCustom(CODE_EXCEPTION, "PAGE NO OR PAGE SIZE INVALID!");
            }
            listTxn = txnDao.getAllTxn(req.getPage_no(), req.getPage_size());
            if (listTxn != null) {
                final StringWriter sw = new StringWriter();
                final ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(sw, listTxn);
                String jsonList = sw.toString();
                sw.close();
                return setResponseCustom(CODE_SUCCESS, jsonList);
            } else {
                return setResponseCustom(CODE_SUCCESS, "DATA EMPTY!");
            }
        } catch (Exception ex) {
           return setResponseCustom(CODE_EXCEPTION, "API ERROR!");
        }
    }
}

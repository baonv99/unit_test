package com.vnpay.springapigradle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vnpay.springapigradle.common.Utils;
import com.vnpay.springapigradle.entitites.ReqAllTxn;
import com.vnpay.springapigradle.entitites.ReqTxn;
import com.vnpay.springapigradle.entitites.RspObj;
import com.vnpay.springapigradle.service.TxnService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.vnpay.springapigradle.common.Utils.*;

@RestController
public class TxnController {
    @Autowired
    TxnService txnService;
    public final Logger log = LoggerFactory.getLogger("request_api");
    public ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    // 00 SUCCESS
    // 01 MISSING PARAMS
    @RequestMapping(value = "txn-create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public RspObj txnCreate(@RequestBody ReqTxn req) {
        RspObj rspObj = new RspObj();
        try {
            System.out.println("Create!!");
            if (StringUtils.isEmpty(String.valueOf(req.getRequest_id())) || StringUtils.isEmpty(String.valueOf(req.getTxn_id()))) {
                String error_msg = "Missing Params";
                rspObj.setCode(CODE_MISSING);
                rspObj.setMessage(error_msg);
                rspObj.setRsp_time(Utils.getDateNowToString("ddMMyyyyHHmmss"));
                writeLogRequest("LINK: /txn-create - METHOD: POST - REQUEST", 0, error_msg, 2);
                return rspObj;
            } else {
                writeLogRequest("LINK: /txn-create - METHOD: POST - REQUEST", req.getRequest_id(), req, 1);
                rspObj = txnService.createTxn(req);
                writeLogRequest("LINK: /txn-create - METHOD: POST - RESPONSE", req.getRequest_id(), req, 1);
                return rspObj;
            }
        } catch (Exception ex) {
            rspObj.setCode(CODE_EXCEPTION);
            rspObj.setMessage("Error");
            rspObj.setRsp_time(Utils.getDateNowToString("ddMMyyyyHHmmss"));
            writeLogRequest("LINK: /txn-create - METHOD: POST - REQUEST", req.getRequest_id(), ex.getMessage(), 2);
            return rspObj;
        }
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "txn-update", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public RspObj txnUpdate(@RequestBody ReqTxn req) {
        try {
            if (StringUtils.isEmpty(String.valueOf(req.getRequest_id())) || StringUtils.isEmpty(String.valueOf(req.getTxn_id()))) {
                String error_msg =  "Missing Params";
                writeLogRequest("LINK: /txn-update - METHOD: POST - REQUEST", 0, error_msg, 2);
                return setResponseCustom(CODE_MISSING, error_msg);
            } else {
                RspObj rspObj = new RspObj();
                writeLogRequest("LINK: /txn-update - METHOD: POST - REQUEST", req.getRequest_id(), req, 1);
                rspObj = txnService.updateTxn(req);
                writeLogRequest("LINK: /txn-update - METHOD: POST - RESPONSE", req.getRequest_id(), req, 1);
                return rspObj;
            }
        } catch (Exception ex) {
            writeLogRequest("LINK: /txn-update - METHOD: POST - RESPONSE", req.getRequest_id(), ex.getMessage(), 2);
            return setResponseCustom(CODE_EXCEPTION, ex.getMessage());
        }
    }

    @RequestMapping(value = "txn-list-all", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    public RspObj txnListAll(@RequestBody ReqAllTxn req) {
        if (StringUtils.isEmpty(String.valueOf(req.getRequest_id()))) {
            String error_msg =  "Missing Request ID";
            writeLogRequest("LINK: /txn-update - METHOD: POST - REQUEST", 0, error_msg, 2);
            return setResponseCustom(CODE_MISSING, error_msg);
        } else {
            try {
                RspObj rspObj = new RspObj();
                writeLogRequest("LINK: /txn-list-all - METHOD: POST - REQUEST - FILE", req.getRequest_id(), req, 1);
                rspObj = txnService.getAllTxn(req);
                writeLogRequest("LINK: /txn-list-all - METHOD: POST - RESPONSE - FILE", req.getRequest_id(), req, 1);
                return rspObj;
            } catch (Exception ex) {
                writeLogRequest("LINK: /txn-list-all - METHOD: POST - REQUEST - FILE", req.getRequest_id(), req, 1);
                writeLogRequest("LINK: /txn-list-all - METHOD: POST - RESPONSE - FILE", req.getRequest_id(), ex.getMessage(), 2);
                return setResponseCustom(CODE_EXCEPTION, ex.getMessage());
            }
        }
    }

    @RequestMapping(value = "txn-get-id/{id}/{request_id}", method = RequestMethod.GET)
    public RspObj txnGetId(@PathVariable("id") long id, @PathVariable("request_id") int request_id, @RequestHeader("data") String data) {
        log.info("Request Headers: " + data);
        if (request_id <= 0) {
            String error_msg = "Missing Request ID";
            return setResponseCustom(CODE_MISSING, error_msg);
        } else {
            try {
                writeLogRequest("LINK: /txn-list-all/" + id + "/"  + request_id + " - METHOD: GET - REQUEST", request_id, id, 1);
                RspObj rspObj = new RspObj();
                rspObj = txnService.getTxnID(id);
                writeLogRequest("LINK: /txn-list-all/" + id + "/"  + request_id + " - METHOD: GET - REQUEST", request_id, rspObj, 1);
                return rspObj;
            } catch (Exception ex) {
                writeLogRequest("LINK: /txn-list-all/" + id + "/"  + request_id + "- METHOD: POST - RESPONSE", request_id, ex.getMessage(), 2);
                return setResponseCustom(CODE_EXCEPTION, ex.getMessage());
            }
        }
    }
    public void writeLogRequest(String link_method_type, long request_id, Object objectJson, int type_log) {
        try {
            if (type_log == 1) {
                String jsonRequestCreate = ow.writeValueAsString(objectJson);
                log.info("ID [{}] - {} - DATA: {}", request_id, link_method_type, jsonRequestCreate);
            } else {
                log.error("ID [{}] - {} - MESSAGE: {}", request_id, link_method_type, objectJson);
            }
        } catch (JsonProcessingException e) {
            log.error("ID [{}] - CAN'T PROCESSING JSON", request_id);
            throw new RuntimeException(e);
        }
    }
}

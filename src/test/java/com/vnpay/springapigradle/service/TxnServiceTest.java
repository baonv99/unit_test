package com.vnpay.springapigradle.service;


import com.vnpay.springapigradle.dao.TxnDao;
import com.vnpay.springapigradle.entitites.ReqAllTxn;
import com.vnpay.springapigradle.entitites.ReqTxn;
import com.vnpay.springapigradle.entitites.RspObj;
import com.vnpay.springapigradle.entitites.Txn;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TxnServiceTest {
    @InjectMocks
    TxnService txnService;
    @Mock
    TxnDao txnDao;
    @Mock
    ReqTxn reqTxn;
    @Mock
    ReqAllTxn reqAllTxn;
    @Mock
    Txn txn;

    @BeforeEach
    void init() {
        reqTxn = new ReqTxn();
        reqTxn.setTxn_id(123456);
        reqTxn.setRequest_id(123456);
        reqTxn.setCode("00");
        reqTxn.setEmail("Baonguyen091099@gmail.com");
        reqTxn.setGender("1");
        reqTxn.setName("Nguyễn Văn A");
        reqTxn.setPhone_number("0123345656");
        reqTxn.setCreated_by("Baonv");
        reqTxn.setMessage("Insert success!!");
        txn = new Txn();
        reqAllTxn = new ReqAllTxn();
        reqAllTxn.setPage_no(1);
        reqAllTxn.setPage_size(10);
    }
    // Test Create Txn
    @Test
    @DisplayName("Test Service Insert Txn Success")
    public void service_createTxn_txnDaoSuccess() {
        Mockito.when(txnDao.createTxn(ArgumentMatchers.any())).thenReturn(true);
        RspObj rspObj = txnService.createTxn(reqTxn);
        Assertions.assertEquals("00", rspObj.getCode());
        Assertions.assertEquals("Thêm mới thành công!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Service Create TxnDao Error")
    public void service_createTxn_txnDaoError() {
        Mockito.when(txnDao.createTxn(ArgumentMatchers.any())).thenReturn(false);
        RspObj rspObj = txnService.createTxn(reqTxn);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("Có lỗi trong quá trình thêm mới!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Service Create Txn Throw Exception")
    public void service_createTxn_throwException() {
        Mockito.when(txnDao.createTxn(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        RspObj rspObj = txnService.createTxn(reqTxn);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("API ERROR!", rspObj.getMessage());
    }
    // Test Update Txn
    @Test
    @DisplayName("Test Service Update TxnDao Success")
    public void service_updateTxn_txnDaoSuccess() {
        Mockito.when(txnDao.updateTxn(ArgumentMatchers.any())).thenReturn(true);
        RspObj rspObj = txnService.updateTxn(reqTxn);
        Assertions.assertEquals("00", rspObj.getCode());
        Assertions.assertEquals("Cập nhật thành công!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Service Update TxnDao Error")
    public void service_updateTxn_txnDaoError() {
        Mockito.when(txnDao.updateTxn(ArgumentMatchers.any())).thenReturn(false);
        RspObj rspObj = txnService.updateTxn(reqTxn);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("Có lỗi xảy ra trong quá trình cập nhật!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Service Update Txn Throw Exception")
    public void service_updateTxn_throwException() {
        Mockito.when(txnDao.updateTxn(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        RspObj rspObj = txnService.updateTxn(reqTxn);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("API ERROR!", rspObj.getMessage());
    }
    // Test Get Txn ID
    @Test
    @DisplayName("Test Get ID Txn - Txn Dao Success")
    public void service_getById_txnDaoSuccess() {
        txn.setTxn_id(123456L);
        Mockito.when(txnDao.getTxnId(ArgumentMatchers.anyLong())).thenReturn(txn);
        RspObj rspObj = txnService.getTxnID(1L);
        System.out.println(rspObj);
        Assertions.assertEquals("00", rspObj.getCode());
    }
    @Test
    @DisplayName("Test Get ID Txn - TxnDao Error")
    public void service_getById_txnDaoError() {
        RspObj rspObj = txnService.getTxnID(1L);
        Mockito.when(txnDao.getTxnId(ArgumentMatchers.anyLong())).thenReturn(null);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("NOT FOULD TXN ID", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Get ID Txn - TxnDao Exception")
    public void service_getById_throwException() {
        Mockito.when(txnDao.getTxnId(ArgumentMatchers.anyLong())).thenThrow(RuntimeException.class);
        RspObj rspObj = txnService.getTxnID(1L);
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("API ERROR!", rspObj.getMessage());
    }

    // Test Get All Txn
    @Test
    @DisplayName("Test Get All Txn - Txn Dao Success")
    public void service_getAll_txnDaoSuccess() {
        List listTxn = new ArrayList<Txn>();
        txn.setTxn_id(123456L);
        listTxn.add(txn);
        ReqAllTxn reqAll = new ReqAllTxn();
        reqAll.setPage_no(1);
        reqAll.setPage_size(20);
        Mockito.when(txnDao.getAllTxn(ArgumentMatchers.anyLong(),ArgumentMatchers.anyLong())).thenReturn((ArrayList<Txn>) listTxn);
        RspObj rspObj = txnService.getAllTxn(reqAll);
        System.out.println(rspObj.getMessage());
        Assertions.assertEquals(123456L, txn.getTxn_id());
    }
    @Test
    @DisplayName("Test Get All Txn - Txn Dao Success - Page Invalid")
    public void service_getAll_txnDaoSuccess_pageInvalid() {
        List listTxn = new ArrayList<Txn>();
        txn.setTxn_id(123456L);
        listTxn.add(txn);
        ReqAllTxn reqAll = new ReqAllTxn();
        reqAll.setPage_no(-1);
        reqAll.setPage_size(-1);
        Mockito.when(txnDao.getAllTxn(ArgumentMatchers.anyLong(),ArgumentMatchers.anyLong())).thenReturn((ArrayList<Txn>) listTxn);
        RspObj rspObj = txnService.getAllTxn(reqAll);
        System.out.println(rspObj.getMessage());
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("PAGE NO OR PAGE SIZE INVALID!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Get All Txn - Txn Dao Get Data Empty")
    public void service_getAll_txnDaoError() {
        ReqAllTxn reqAll = new ReqAllTxn();
        reqAll.setPage_no(1);
        reqAll.setPage_size(20);
        Mockito.when(txnDao.getAllTxn(ArgumentMatchers.anyLong(),ArgumentMatchers.anyLong())).thenReturn(null);
        RspObj rspObj = txnService.getAllTxn(reqAll);
        System.out.println(rspObj.getMessage());
        Assertions.assertEquals("00", rspObj.getCode());
        Assertions.assertEquals("DATA EMPTY!", rspObj.getMessage());
    }
    @Test
    @DisplayName("Test Get All Txn - Txn Dao Get Data Exception")
    public void service_getAll_throwException() {
        ReqAllTxn reqAll = new ReqAllTxn();
        reqAll.setPage_no(1);
        reqAll.setPage_size(20);
        Mockito.when(txnDao.getAllTxn(ArgumentMatchers.anyLong(),ArgumentMatchers.anyLong())).thenThrow(NullPointerException.class);
        RspObj rspObj = txnService.getAllTxn(reqAll);
        System.out.println(rspObj.getMessage());
        Assertions.assertEquals("99", rspObj.getCode());
        Assertions.assertEquals("API ERROR!", rspObj.getMessage());
    }
}
package com.vnpay.springapigradle.dao;

import com.vnpay.springapigradle.entitites.Txn;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class TxnDaoTest {
    @InjectMocks
    TxnDao txnDao;
    @Mock
    Txn txn;
    @Mock
    Connection cnn;
    @Mock
    CallableStatement cstmt;
    @Mock
    DataSource dataSource;

    @Mock
    ResultSet resultSet;
    @BeforeEach
    void init() {
        txn = new Txn();
        txn.setTxn_id(123456L);
        txn.setCode("00");
        txn.setEmail("Baonguyen091099@gmail.com");
        txn.setGender("1");
        txn.setName("Nguyễn Văn A");
        txn.setPhone_number("0123345656");
        txn.setCreated_by("Baonv");
        txn.setMessage("Insert success!!");
    }
    // Create Txn
    @Test
    @DisplayName("Dao create Txn Connection Error")
    void dao_createTxn_getConnection() {
        Mockito.when(txnDao.getConnection()).thenReturn(null);
        Assertions.assertEquals(false, txnDao.createTxn(txn));
    }
    @Test
    @DisplayName("Dao create Txn Connect Success")
    void dao_createTxn_success() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenReturn(cstmt);
        Mockito.when(cstmt.executeQuery()).thenReturn(resultSet);
        Assertions.assertEquals(true, txnDao.createTxn(txn));
    }
    @Test
    @DisplayName("Dao create Txn Connect Error")
    void dao_createTxn_error() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(false, txnDao.createTxn(txn));
    }

    // Update Txn
    @Test
    @DisplayName("Dao Update Txn Connection Error")
    void dao_updateTxn_getConnectError() {
        Mockito.when(txnDao.getConnection()).thenReturn(null);
        Assertions.assertEquals(false, txnDao.createTxn(txn));
    }
    @Test
    @DisplayName("Dao Update Txn Success")
    void dao_updateTxn_success() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenReturn(cstmt);
        Mockito.when(cstmt.executeQuery()).thenReturn(resultSet);
        Assertions.assertEquals(true, txnDao.createTxn(txn));
    }

    @Test
    @DisplayName("Dao Update Txn Error")
    void dao_updateTxn_error() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(false, txnDao.updateTxn(txn));
    }
    // Get Txn Id
    @Test
    @DisplayName("Dao Get Txn ID Connection Error")
    void dao_getTxnId_getConnectError() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(null);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(null, txnDao.getTxnId(1L));
    }
    @Test
    @DisplayName("Dao Get Txn ID Success")
    void dao_getTxnId_Success() throws SQLException {
//        txn.setTxn_id(123L);
//        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
//        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenReturn(cstmt);
//        Mockito.when(cstmt.executeQuery()).thenReturn(resultSet);
//        Mockito.when(cstmt.getObject(ArgumentMatchers.anyInt())).thenReturn(txn);
//        Assertions.assertEquals(123L, txnDao.getTxnId(1L).getTxn_id());
    }
    @Test
    @DisplayName("Dao Get Txn ID Error")
    void dao_getTxnId_Error() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(null, txnDao.getTxnId(1L));
    }

    // Get All txn
    @Test
    @DisplayName("Dao Get All Txn Connection Error")
    void dao_getAllTxn_getConnectionError() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(null);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(null, txnDao.getAllTxn(1, 20));
    }
    @Test
    @DisplayName("Dao Get All Txn Success")
    void dao_getAllTxn_success() {

    }

    @Test
    @DisplayName("Dao Get All Txn Error")
    void dao_getAllTxn_error() throws SQLException {
        Mockito.when(txnDao.getConnection()).thenReturn(cnn);
        Mockito.when(cnn.prepareCall(ArgumentMatchers.any())).thenThrow(NullPointerException.class);
        Assertions.assertEquals(null, txnDao.getAllTxn(1, 20));
    }
}
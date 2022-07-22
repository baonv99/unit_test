package com.vnpay.springapigradle.dao;

import com.vnpay.springapigradle.entitites.Txn;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.CallableStatement;
import java.sql.*;
import java.util.ArrayList;

@Component
public class TxnDao extends BaseDao {
    @Value("${app.db.schema}")
    public String schemaName;

    public boolean createTxn(Txn txn) {
        boolean rs = false;
        Connection cnn = getConnection();
        if (cnn == null) {
            log.error("[{}] Can not get connection", "createTxn in TxnDAO");
            return false;
        }
        try {
            CallableStatement cstmt = cnn.prepareCall("{call " + schemaName + ".LAB_TXN.CREATE_TXN(?,?,?,?,?,?,?,?,?,?,?)}");
            int i = 1;
            cstmt.setLong(i++, txn.getTxn_id());
            cstmt.setTimestamp(i++, (Timestamp)txn.getTxn_date());
            cstmt.setString(i++, txn.getName());
            cstmt.setTimestamp(i++, (Timestamp)txn.getUpdated_date());
            cstmt.setString(i++, txn.getUpdated_by());
            cstmt.setString(i++, txn.getName());
            cstmt.setString(i++, txn.getEmail());
            cstmt.setString(i++, txn.getPhone_number());
            cstmt.setString(i++, txn.getGender());
            cstmt.setString(i++, txn.getCode());
            cstmt.setString(i++, txn.getMessage());
            cstmt.executeQuery();
            rs = true;
        } catch (Exception ex) {
            log.error("[{}] Error exception: {}","createTxn in TxnDAO", ex.getMessage());
            rs = false;
        } finally {
            closeConnection(cnn);
        }
        return rs;
    }
    public boolean updateTxn(Txn txn) {
         Connection cnn = getConnection();
         if (cnn == null) {
             log.error("[{}] Can not get connection", "updateTxn in TxnDAO");
             return false;
         }
         try {
             CallableStatement cstmt = cnn.prepareCall("{call " + schemaName + ".LAB_TXN.UPDATE_TXN(?,?,?,?,?,?,?,?,?)}");
             int i = 1;
             cstmt.setLong(i++, txn.getTxn_id());
             cstmt.setTimestamp(i++, (Timestamp)txn.getUpdated_date());
             cstmt.setString(i++, txn.getUpdated_by());
             cstmt.setString(i++, txn.getName());
             cstmt.setString(i++, txn.getEmail());
             cstmt.setString(i++, txn.getPhone_number());
             cstmt.setString(i++, txn.getGender());
             cstmt.setString(i++, txn.getCode());
             cstmt.setString(i++, txn.getMessage());
             cstmt.executeQuery();
             return true;
         } catch (Exception ex) {
             log.error("[{}] Error exception: {}","createTxn in TxnDAO", ex.getMessage());
         } finally {
             closeConnection(cnn);
         }
         return false;
    }

    public Txn getTxnId(long txn_id) {
        Connection cnn = getConnection();
        if (cnn == null) {
            log.error("[{}] Can not get connection", "getTxnId in TxnDAO");
            return null;
        }
        Txn txn = null;
        try {
            CallableStatement cstmt = cnn.prepareCall("{call " + schemaName + ".LAB_TXN.GET_TXN_ID(?,?)}");
            cstmt.setLong(1, txn_id);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            if (rs.next()) {
                txn = new Txn();
                txn.setTxn_id(rs.getLong("TXN_ID"));
                txn.setTxn_date(rs.getTimestamp("TXN_DATE"));
                txn.setCreated_by(rs.getString("CREATED_BY"));
                txn.setUpdated_date(rs.getTimestamp("UPDATED_DATE"));
                txn.setUpdated_by(rs.getString("UPDATED_BY"));
            }
            closeRs(rs);
            closeCstmt(cstmt);
        } catch (Exception ex) {
            log.error("[{}] Error exception: {}","getTxnId in TxnDAO", ex.getMessage());
            return null;
        } finally {
            closeConnection(cnn);
        }
        return txn;
    }

    public ArrayList<Txn> getAllTxn(long page_no, long page_size) {
        Connection cnn = getConnection();
        if (cnn == null) {
            log.error("[{}] Can not get connection", "getAllTxn in TxnDAO");
            return null;
        }
        ArrayList<Txn> listTxn = new ArrayList<Txn>();
        try {
            CallableStatement cstmt = cnn.prepareCall("{call " + schemaName + ".LAB_TXN.GET_ALL_TXN(?,?,?,?)}");
            cstmt.setLong(1, page_no);
            cstmt.setLong(2, page_size);
            cstmt.registerOutParameter(3, OracleTypes.NUMBER);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(4);
            while (rs.next()) {
                Txn txn = new Txn();
                txn.setTxn_id(rs.getLong("TXN_ID"));
                txn.setTxn_date(rs.getTimestamp("TXN_DATE"));
                txn.setCreated_by(rs.getString("CREATED_BY"));
                txn.setUpdated_date(rs.getTimestamp("UPDATED_DATE"));
                txn.setUpdated_by(rs.getString("UPDATED_BY"));
                listTxn.add(txn);
            }
            closeRs(rs);
            closeCstmt(cstmt);
        } catch (Exception ex) {
            log.error("[{}] Error exception: {}", "getAllTxn in TxnDAO", ex.getMessage());
            return null;
        } finally {
            closeConnection(cnn);
        }
        return listTxn;
    }
}

package com.vnpay.springapigradle.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
    final Logger log = LoggerFactory.getLogger("request_api");

    @Autowired
    DataSource dataSource;

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            log.error("Cannot connect {}", ex.getMessage());
            return null;
        }
    }

    protected void closeConnection(Connection cnn) {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException ex) {
            log.error("Cannot close connect {}", ex.getMessage());
        }
    }
    protected void closeCstmt(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException ex) {
            log.error("{}", ex);
        }

    }

    protected void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            log.error("{}", ex);
        }
    }
}

package com.vnpay.springapigradle.controller;

import com.vnpay.springapigradle.service.TxnService;
import name.falgout.jeffrey.testing.junit.mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class TxnControllerTest {
    @Mock
    TxnService txnService;

    // Controller
    // C - txn-create
    // C - txn-create-success
    // C - txn-create-method-fail
    // C - txn-create-content-request-invalid-json
    // C - txn-create-content-request-missing-request-id
    // C - txn-create-content-request-missing-txn-id
    // C - txn-create-content-request-missing-all-param
    // C - txn-update
    // C - txn-update-success
    // C - txn-update-method-fail
    // C - txn-update-content-request-invalid-json
    // C - txn-update-content-request-missing-request-id
    // C - txn-update-content-request-missing-txn-id
    // C - txn-update-content-request-missing-all-param
    // C - txn-list-all
    // C - txn-list-all-success
    // C - txn-list-all-method-fail
    // C - txn-list-all-request-missing-request-id
    // C - txn-list-all
    // C - txn-get-id/{id}/{request_id}
    // C - txn-get-id/{id}/{request_id} success
    // C - txn-get-id/{id}/{request_id} method fail
    // C - txn-get-id/{id}/{request_id} missing id
    // C - txn-get-id/{id}/{request_id} missing request_id
    // C - txn-get-id/{id}/{request_id} missing id and request id
    @Test
    void txnCreate() {
    }

    @Test
    void txnUpdate() {
    }

    @Test
    void txnListAll() {
    }

    @Test
    void txnGetId() {
    }

}
package com.vnpay.springapigradle.entitites;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReqTxn {
    private long txn_id;
    private Date txn_date;
    private String created_by;
    private Date updated_date;
    private String updated_by;
    private String code;
    private String message;
    private String gender;
    private String name;
    private String email;
    private String phone_number;
    private int rownumber;
    private long request_id;
}

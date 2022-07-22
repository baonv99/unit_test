package com.vnpay.springapigradle.entitites;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Txn {
    private Long txn_id;
    private Long request_id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date txn_date;
    private String created_by;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updated_date;
    private String updated_by;
    private String code;
    private String message;
    private String gender;
    private String name;
    private String email;
    private String phone_number;
    private int rownumber;
}

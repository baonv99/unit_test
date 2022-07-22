package com.vnpay.springapigradle.entitites;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqAllTxn {
    private int page_no;
    private int page_size;
    private int request_id;
}

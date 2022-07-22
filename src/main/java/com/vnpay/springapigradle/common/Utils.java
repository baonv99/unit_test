package com.vnpay.springapigradle.common;

import com.vnpay.springapigradle.entitites.RspObj;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static final String CODE_SUCCESS = "00";
    public static final String CODE_MISSING = "01";
    public static final String CODE_EXCEPTION = "99";

    public static String getDateNowToString(String pattern) {
        Date dtNow = Calendar.getInstance().getTime();
        Format fm = new SimpleDateFormat(pattern);//2017050403020
        return fm.format(dtNow);
    }
    public static RspObj setResponseCustom(String code, String message) {
        RspObj rspObj = new RspObj();
        rspObj.setCode(code);
        rspObj.setMessage(message);
        rspObj.setRsp_time(Utils.getDateNowToString("ddMMyyyyHHmmss"));
        return rspObj;
    }
}

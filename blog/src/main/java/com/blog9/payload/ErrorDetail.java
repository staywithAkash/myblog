package com.blog9.payload;

import java.util.Date;

public class ErrorDetail {
    private Date date;
    private String msg;
    private String des;

    public ErrorDetail(Date date, String msg, String des) {
        this.date = date;
        this.msg = msg;
        this.des = des;
    }

    public Date getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }

    public String getDes() {
        return des;
    }
}

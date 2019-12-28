package com.juntian.rxjavaretrofitmvvm.model.bean;

import java.io.IOException;

/**
 * @作者:TJ
 * @时间:2018/7/24 9:56
 * @描述:
 */
public class ApiException extends IOException {

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ApiException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}

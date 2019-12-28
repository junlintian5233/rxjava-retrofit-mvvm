package com.junzhitian.rxjavaretrofitmvvm.model.bean;

/**
 * @作者:TJ
 * @时间:2018/7/24 9:32
 * @描述:
 */
public class HttpResult<T> {

    private int    status;
    private String msg;
    private T      data;

    public HttpResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public HttpResult() {

    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

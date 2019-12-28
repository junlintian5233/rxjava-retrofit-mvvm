package com.juntian.rxjavaretrofitmvvm.constant;

/**
 * @作者:TJ
 * @时间:2019/8/28
 * @描述:
 */
public interface OrderState {
    String WAITINT_PAY      = "10";
    String WAITINT_DELIVERY = "20";
    String WAITINT_RECEIVE  = "30";
    String WAITINT_COMMENT  = "40";
    String CANCEL           = "70";
    String ALL              = "0";
}

package com.juntian.rxjavaretrofitmvvm.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @作者:TJ
 * @时间:2019/9/5
 * @描述:
 */
public class WechatPayInfo {

    @Override
    public String toString() {
        return "WechatPayInfo{" +
                "appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", packageX='" + packageX + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    /**
     * appid : wx7ca7ee9ffd8a67aa
     * partnerid : 1528020101
     * package : Sign=WXPay
     * noncestr : xusprfas4yx9hmqw8eqvlzvapofwpig1
     * timestamp : 1554950646
     * prepayid : wx1110440617734450208daa0d1473402640
     * sign : 5674FCD79E1296AFEBF3F2C1F3CF2359
     */

    private String appid;
    private String partnerid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String    timestamp;
    private String prepayid;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

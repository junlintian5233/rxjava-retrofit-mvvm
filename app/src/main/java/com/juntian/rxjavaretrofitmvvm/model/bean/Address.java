package com.juntian.rxjavaretrofitmvvm.model.bean;

import java.io.Serializable;

/**
 * @作者:TJ
 * @时间:2019/8/14
 * @描述:
 */
public class Address implements Serializable {

    /**
     * id : 10
     * real_name : 朱先生
     * mobile : 13260693618
     * city_title : 广东省汕头市南澳县
     * address : 武汉光谷广场
     * province : 1
     * city : 2
     * district : 3
     * is_default : 1
     */

    private String id;
    private String real_name;
    private String mobile;
    private String city_title;
    private String address;
    private String province;
    private String city;
    private String district;
    private String is_default;
    private String map_lng;
    private String map_lat;
    private String mobile_title;


    public String getMap_lng() {
        return map_lng;
    }

    public void setMap_lng(String map_lng) {
        this.map_lng = map_lng;
    }

    public String getMap_lat() {
        return map_lat;
    }

    public void setMap_lat(String map_lat) {
        this.map_lat = map_lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity_title() {
        return city_title;
    }

    public void setCity_title(String city_title) {
        this.city_title = city_title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getMobile_title() {
        return mobile_title;
    }

    public void setMobile_title(String mobile_title) {
        this.mobile_title = mobile_title;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", real_name='" + real_name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", city_title='" + city_title + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", is_default='" + is_default + '\'' +
                ", mobile_title='" + mobile_title + '\'' +
                '}';
    }
}

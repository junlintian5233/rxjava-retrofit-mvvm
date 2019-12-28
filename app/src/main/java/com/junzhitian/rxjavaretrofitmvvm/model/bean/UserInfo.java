package com.junzhitian.rxjavaretrofitmvvm.model.bean;

import java.io.Serializable;

public class UserInfo implements Serializable {


    /**
     * nickname : 君
     * gender : 1
     * birthday : 2019-01-27
     * mobile : 13666666666
     * email :
     * avatar : http://test.cnsunrun.com/wendujianceapp/Uploads/Avatar/20190717/5d2fde0f86adf.png
     * member_id : 108
     * province : 130
     * city : 131
     * area : 132
     * province_text : 中国
     * city_text : 北京
     * area_text : 东城
     * address : 红红火火混
     * lang_id : 1
     */

    private String nickname;
    private String gender;
    private String birthday;
    private String mobile;
    private String email;
    private String avatar;
    private String member_id;
    private String province;
    private String city;
    private String area;
    private String province_text;
    private String city_text;
    private String area_text;
    private String address;
    private String lang_id;
    private String token;
    /**
     * id : 108
     * role_id : 0
     * password : d2e687c634aea2b338534a83ef49d90d
     * salt : r3ic54
     * deal_password :
     * deal_salt :
     * is_hid : 0
     * is_del : 0
     * qq_open_id :
     * sina_open_id :
     * mini_openid :
     * pc_openid :
     * weixin_open_id :
     * login_ip : 27.16.220.98
     * login_time : 2019-07-30 03:26:19
     * register_time : 2019-07-14 22:47:18
     * register_ip : 192.168.2.101
     * balance : 0.00
     * frozen : 0.00
     * status : 1
     * register_type : 0
     * max_share_num : 0
     * is_extract : 0
     */

    private String id;
    private String role_id;
    private String password;
    private String salt;
    private String deal_password;
    private String deal_salt;
    private String is_hid;
    private String is_del;
    private String qq_open_id;
    private String sina_open_id;
    private String mini_openid;
    private String pc_openid;
    private String weixin_open_id;
    private String login_ip;
    private String login_time;
    private String register_time;
    private String register_ip;
    private String balance;
    private String frozen;
    private String status;
    private String register_type;
    private String max_share_num;
    private String is_extract;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince_text() {
        return province_text;
    }

    public void setProvince_text(String province_text) {
        this.province_text = province_text;
    }

    public String getCity_text() {
        return city_text;
    }

    public void setCity_text(String city_text) {
        this.city_text = city_text;
    }

    public String getArea_text() {
        return area_text;
    }

    public void setArea_text(String area_text) {
        this.area_text = area_text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", member_id='" + member_id + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", province_text='" + province_text + '\'' +
                ", city_text='" + city_text + '\'' +
                ", area_text='" + area_text + '\'' +
                ", address='" + address + '\'' +
                ", lang_id='" + lang_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDeal_password() {
        return deal_password;
    }

    public void setDeal_password(String deal_password) {
        this.deal_password = deal_password;
    }

    public String getDeal_salt() {
        return deal_salt;
    }

    public void setDeal_salt(String deal_salt) {
        this.deal_salt = deal_salt;
    }

    public String getIs_hid() {
        return is_hid;
    }

    public void setIs_hid(String is_hid) {
        this.is_hid = is_hid;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getQq_open_id() {
        return qq_open_id;
    }

    public void setQq_open_id(String qq_open_id) {
        this.qq_open_id = qq_open_id;
    }

    public String getSina_open_id() {
        return sina_open_id;
    }

    public void setSina_open_id(String sina_open_id) {
        this.sina_open_id = sina_open_id;
    }

    public String getMini_openid() {
        return mini_openid;
    }

    public void setMini_openid(String mini_openid) {
        this.mini_openid = mini_openid;
    }

    public String getPc_openid() {
        return pc_openid;
    }

    public void setPc_openid(String pc_openid) {
        this.pc_openid = pc_openid;
    }

    public String getWeixin_open_id() {
        return weixin_open_id;
    }

    public void setWeixin_open_id(String weixin_open_id) {
        this.weixin_open_id = weixin_open_id;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getRegister_ip() {
        return register_ip;
    }

    public void setRegister_ip(String register_ip) {
        this.register_ip = register_ip;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFrozen() {
        return frozen;
    }

    public void setFrozen(String frozen) {
        this.frozen = frozen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegister_type() {
        return register_type;
    }

    public void setRegister_type(String register_type) {
        this.register_type = register_type;
    }

    public String getMax_share_num() {
        return max_share_num;
    }

    public void setMax_share_num(String max_share_num) {
        this.max_share_num = max_share_num;
    }

    public String getIs_extract() {
        return is_extract;
    }

    public void setIs_extract(String is_extract) {
        this.is_extract = is_extract;
    }
}

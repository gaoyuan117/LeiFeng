package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/17.
 */

public class LoginBean {

    /**
     * uid : 7
     * nickname : 孙常栋
     * mobile : 18396801272
     * city_id : 1
     * city_info : 高密市
     * address : 来咯的样子
     * token : 6e6a4fea308f88f1ad0c95c38c5fafd2ddb8cabeaa46641c1e41135acdc485f6
     */

    private int uid;
    private String nickname;
    private String mobile;
    private int city_id;
    private String city_info;
    private String address;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_info() {
        return city_info;
    }

    public void setCity_info(String city_info) {
        this.city_info = city_info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

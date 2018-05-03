package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class JoinProjectUserBean {

    /**
     * id : 4
     * uid : 4
     * activity_id : 4
     * status : 1
     * add_time : 1524271970
     * reason : null
     * id_type : 0
     * id_no : 370826199510046110
     * mobile : 15863547451
     * user_nickname : 张三
     * avatar : admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
     * service_hour : 0
     */

    private int id;
    private int uid;
    private int activity_id;
    private int status;
    private int add_time;
    private Object reason;
    private int id_type;
    private String id_no;
    private String mobile;
    private String user_nickname;
    private String avatar;
    private int service_hour;
    private int myStatus = 0;

    public int getMyStatus() {
        return myStatus;
    }

    public void setMyStatus(int myStatus) {
        this.myStatus = myStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(Object reason) {
        this.reason = reason;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getService_hour() {
        return service_hour;
    }

    public void setService_hour(int service_hour) {
        this.service_hour = service_hour;
    }
}

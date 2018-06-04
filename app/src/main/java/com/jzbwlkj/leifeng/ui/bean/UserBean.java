package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/5/31.
 */

public class UserBean {

    /**
     * id : 7
     * user_nickname : admin
     * avatar : http://leifeng.jzbwlkj.com/upload/default/20180508/aaed8f7ab88eb41f3dc5bf08cd19b846.jpg
     * service_hour : 0.00
     */

    private int id;
    private String user_nickname;
    private String avatar;
    private String service_hour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getService_hour() {
        return service_hour;
    }

    public void setService_hour(String service_hour) {
        this.service_hour = service_hour;
    }
}

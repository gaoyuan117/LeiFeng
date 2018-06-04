package com.jzbwlkj.leifeng.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TeamInfoBean implements Serializable{

    /**
     * id : 1
     * uid : 1
     * username :
     * password :
     * team_name : 高密市志愿者协会
     * pic : http://leifeng.test.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
     * parent_id : 0
     * area_id : 0
     * contact : 张三
     * contact_mobile : 12521542
     * manager : 张三
     * manager_mobile : 152121
     * desc : <p>时代发生地方的风格的风格的</p>
     * service_hour : 0.00
     * add_time : 1522461417
     * audit_time : 1522461417
     * status : 1
     * sign_auth : 0
     * note :
     * join_info : {"status":0,"note":"","add_time":1524103521}
     */


    private int id;
    private int uid;
    private String username;
    private String password;
    private String team_name;
    private String pic;
    private int parent_id;
    private int area_id;
    private String contact;
    private String contact_mobile;
    private String manager;
    private String manager_mobile;
    private String desc;
    private String service_hour;
    private int add_time;
    private int audit_time;
    private int status;
    private int sign_auth;
    private String note;
    private JoinInfoBean join_info;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager_mobile() {
        return manager_mobile;
    }

    public void setManager_mobile(String manager_mobile) {
        this.manager_mobile = manager_mobile;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getService_hour() {
        return service_hour;
    }

    public void setService_hour(String service_hour) {
        this.service_hour = service_hour;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public int getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(int audit_time) {
        this.audit_time = audit_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSign_auth() {
        return sign_auth;
    }

    public void setSign_auth(int sign_auth) {
        this.sign_auth = sign_auth;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public JoinInfoBean getJoin_info() {
        return join_info;
    }

    public void setJoin_info(JoinInfoBean join_info) {
        this.join_info = join_info;
    }

    public static class JoinInfoBean {
        /**
         * status : 0
         * note :
         * add_time : 1524103521
         */

        private int status;
        private String note;
        private int add_time;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }
}

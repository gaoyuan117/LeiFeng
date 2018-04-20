package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TeamInfoBean {

    /**
     * id : 1
     * uid : 1
     * team_name : 高密市志愿者协会
     * pic : http://leifeng.test.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
     * parent_id : 0
     * contact : 张三
     * contact_mobile : 12521542
     * manager : 张三
     * manager_mobile : 152121
     * desc : &lt;p&gt;时代发生地方的风格的风格的&lt;/p&gt;
     * add_time : 1522461417
     * audit_time : 1522461417
     * status : 1
     * note :
     */

    private int id;
    private int uid;
    private String team_name;
    private String pic;
    private int parent_id;
    private String contact;
    private String contact_mobile;
    private String manager;
    private String manager_mobile;
    private String desc;
    private int add_time;
    private int audit_time;
    private int status;
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

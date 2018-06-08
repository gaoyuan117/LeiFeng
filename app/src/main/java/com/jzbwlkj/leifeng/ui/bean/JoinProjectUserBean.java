package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/24.
 */

public class JoinProjectUserBean {


    /**
     * id : 12
     * uid : 7
     * activity_id : 6
     * type : 0
     * status : 1
     * add_time : 1528094352
     * reason :
     * id_type : 0
     * id_no : 410927198709246035
     * mobile : 18396880731
     * user_nickname : admin
     * avatar : http://leifeng.jzbwlkj.com/upload/default/20180508/aaed8f7ab88eb41f3dc5bf08cd19b846.jpg
     * service_hour : 0.00
     * sign_info : {"time_s":1528440929,"time_e":0,"service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0}
     */

    private int id;
    private int uid;
    private int activity_id;
    private int type;
    private int status;
    private int add_time;
    private String reason;
    private int id_type;
    private String id_no;
    private String mobile;
    private String user_nickname;
    private String avatar;
    private String service_hour;
    private SignInfoBean sign_info;
    private int myStatus;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
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

    public String getService_hour() {
        return service_hour;
    }

    public void setService_hour(String service_hour) {
        this.service_hour = service_hour;
    }

    public SignInfoBean getSign_info() {
        return sign_info;
    }

    public void setSign_info(SignInfoBean sign_info) {
        this.sign_info = sign_info;
    }

    public static class SignInfoBean {
        /**
         * time_s : 1528440929
         * time_e : 0
         * service_hour : 0.00
         * is_normal : 1
         * manager_type : 0
         * manager_uid : 0
         */

        private long time_s;
        private long time_e;
        private String service_hour;
        private int is_normal;
        private int manager_type;
        private int manager_uid;

        public long getTime_s() {
            return time_s;
        }

        public void setTime_s(long time_s) {
            this.time_s = time_s;
        }

        public long getTime_e() {
            return time_e;
        }

        public void setTime_e(long time_e) {
            this.time_e = time_e;
        }

        public String getService_hour() {
            return service_hour;
        }

        public void setService_hour(String service_hour) {
            this.service_hour = service_hour;
        }

        public int getIs_normal() {
            return is_normal;
        }

        public void setIs_normal(int is_normal) {
            this.is_normal = is_normal;
        }

        public int getManager_type() {
            return manager_type;
        }

        public void setManager_type(int manager_type) {
            this.manager_type = manager_type;
        }

        public int getManager_uid() {
            return manager_uid;
        }

        public void setManager_uid(int manager_uid) {
            this.manager_uid = manager_uid;
        }
    }
}

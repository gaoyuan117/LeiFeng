package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class LiuYanBean {
    /**
     * id : 3
     * uid : 7
     * activity_id : 4
     * pid : 0
     * content : 我在这里对留言做出评价
     * status : 0
     * is_sys : 0
     * is_anonymous : 0
     * add_time : 1524297561
     * user_nickname : 孙常栋
     * avatar :
     * reply_info : [{"id":4,"uid":7,"activity_id":4,"pid":3,"content":"继续对留言做出评价","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524297851,"user_nickname":"孙常栋","avatar":""}]
     */

    private int id;
    private int uid;
    private int activity_id;
    private int pid;
    private String content;
    private int status;
    private int is_sys;
    private int is_anonymous;
    private int add_time;
    private String user_nickname;
    private String avatar;
    private List<ReplyInfoBean> reply_info;

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_sys() {
        return is_sys;
    }

    public void setIs_sys(int is_sys) {
        this.is_sys = is_sys;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(int is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
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

    public List<ReplyInfoBean> getReply_info() {
        return reply_info;
    }

    public void setReply_info(List<ReplyInfoBean> reply_info) {
        this.reply_info = reply_info;
    }

    public static class ReplyInfoBean {
        /**
         * id : 4
         * uid : 7
         * activity_id : 4
         * pid : 3
         * content : 继续对留言做出评价
         * status : 0
         * is_sys : 0
         * is_anonymous : 0
         * add_time : 1524297851
         * user_nickname : 孙常栋
         * avatar :
         */

        private int id;
        private int uid;
        private int activity_id;
        private int pid;
        private String content;
        private int status;
        private int is_sys;
        private int is_anonymous;
        private int add_time;
        private String user_nickname;
        private String avatar;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_sys() {
            return is_sys;
        }

        public void setIs_sys(int is_sys) {
            this.is_sys = is_sys;
        }

        public int getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(int is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
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
    }

}

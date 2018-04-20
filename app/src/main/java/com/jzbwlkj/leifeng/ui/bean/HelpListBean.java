package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class HelpListBean {

        /**
         * id : 1
         * uid : 0
         * fullname : 张三
         * mobile : 18396801272
         * address : 山东省济南市历下区
         * content : 这里的山路十八弯
         * is_anonymous : 0
         * is_show : 1
         * add_time : 1524196349
         * reply_content :
         * reply_time : 0
         */

        private int id;
        private int uid;
        private String fullname;
        private String mobile;
        private String address;
        private String content;
        private int is_anonymous;
        private int is_show;
        private long add_time;
        private String reply_content;
        private long reply_time;

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

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(int is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getReply_content() {
            return reply_content;
        }

        public void setReply_content(String reply_content) {
            this.reply_content = reply_content;
        }

        public long getReply_time() {
            return reply_time;
        }

        public void setReply_time(long reply_time) {
            this.reply_time = reply_time;
        }
}

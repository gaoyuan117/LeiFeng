package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ChatListDeticalBean {
        /**
         * id : 1
         * uid : 1
         * title :
         * type : 0
         * content : 测试消息列表
         * activity_id : 0
         * status : 1
         * add_time : 1524109843
         */

        private int id;
        private int uid;
        private String title;
        private int type;
        private String content;
        private int activity_id;
        private int status;
        private long add_time;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }
}

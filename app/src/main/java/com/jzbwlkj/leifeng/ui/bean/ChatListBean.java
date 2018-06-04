package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ChatListBean {

    private List<NewListBean> new_list;
    private List<?> read_list;
    private List<AllListBean> all_list;

    public List<NewListBean> getNew_list() {
        return new_list;
    }

    public void setNew_list(List<NewListBean> new_list) {
        this.new_list = new_list;
    }

    public List<?> getRead_list() {
        return read_list;
    }

    public void setRead_list(List<?> read_list) {
        this.read_list = read_list;
    }

    public List<AllListBean> getAll_list() {
        return all_list;
    }

    public void setAll_list(List<AllListBean> all_list) {
        this.all_list = all_list;
    }

    public static class NewListBean {
        /**
         * id : 1
         * uid : 1
         * title : sdfsdf
         * type : 0
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180410/7874cb041e4f84249237330f1c621ca7.jpg
         * content : <p>测试消息列表</p>
         * activity_id : 0
         * status : 1
         * add_time : 1524109843
         * is_read : 0
         */

        private int id;
        private int uid;
        private String title;
        private int type;
        private String pic;
        private String content;
        private int activity_id;
        private int status;
        private long add_time;
        private int is_read;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }

    public static class AllListBean {
        /**
         * id : 1
         * uid : 1
         * title : sdfsdf
         * type : 0
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180410/7874cb041e4f84249237330f1c621ca7.jpg
         * content : <p>测试消息列表</p>
         * activity_id : 0
         * status : 1
         * add_time : 1524109843
         * is_read : 0
         */

        private int id;
        private int uid;
        private String title;
        private int type;
        private String pic;
        private String content;
        private int activity_id;
        private int status;
        private long add_time;
        private int is_read;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        private String desc;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }

}

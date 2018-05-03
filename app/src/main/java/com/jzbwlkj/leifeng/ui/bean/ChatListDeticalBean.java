package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/19.
 */

public class ChatListDeticalBean {

    /**
     * id : 6
     * uid : 0
     * title : 活动2
     * type : 1
     * pic : http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
     * content : <p>活动详情这里也带着图片</p>
     * activity_id : 4
     * status : 1
     * add_time : 1524271970
     * apply_info : {"status":0,"add_time":1524823970}
     */

    private int id;
    private int uid;
    private String title;
    private int type;
    private String pic;
    private String content;
    private int activity_id;
    private int status;
    private int add_time;
    private ApplyInfoBean apply_info;

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

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public ApplyInfoBean getApply_info() {
        return apply_info;
    }

    public void setApply_info(ApplyInfoBean apply_info) {
        this.apply_info = apply_info;
    }

    public static class ApplyInfoBean {
        /**
         * status : 0
         * add_time : 1524823970
         */

        private int status;
        private int add_time;

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
    }
}

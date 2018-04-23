package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class ProjectBean {

    /**
     * total : 2
     * per_page : 15
     * current_page : 1
     * last_page : 1
     * data : [{"id":4,"uid":4,"team_id":1,"team_name":"高密市志愿者协会","type":1,"title":"活动2","pic":"http://leifeng.jzbwlkj.com/upload/default/20180420/5ef6a1a899b0ae144ac605504c429500.jpg","service_type":3,"join_time_s":1524240000,"join_time_e":1524326400,"start_time":1524672000,"end_time":1524844800,"service_time":"2天","service_hour":3,"service_num":20,"city_id":1,"address":"济南市天桥区","lat":"0.000000","lng":"0.000000","sign_scope":0,"canbu":0,"jiaotongbuzu":0,"baoxianbuzu":0,"peixun":0,"requirement":"<p>活动要求这里带着图片<img src=\"http://leifeng.jzbwlkj.com/upload/default/20180420/29ce36843dbeb291f8ce6c5de2e85402.jpg\" title=\"Lighthouse.jpg\" alt=\"Lighthouse.jpg\"/><\/p>","content":"<p>活动详情这里也带着图片<\/p>","note":"没有相关岗位备注","status":1,"add_time":1524271970,"join_num":1},{"id":3,"uid":4,"team_id":1,"team_name":"高密市志愿者协会","type":1,"title":"测试活动","pic":"","service_type":1,"join_time_s":1524153600,"join_time_e":1524240000,"start_time":1524412800,"end_time":1524672000,"service_time":"2天","service_hour":2,"service_num":20,"city_id":1,"address":"济南市天桥区","lat":"0.000000","lng":"0.000000","sign_scope":0,"canbu":0,"jiaotongbuzu":0,"baoxianbuzu":0,"peixun":0,"requirement":"<p><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动要求<\/spa","content":"<p><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><span style=\"color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);\">活动详情<\/span><img src=\"http://leifeng.jzbwlkj.com/upload/default/20180420/29ce36843dbeb291f8ce6c5de2e85402.jpg\" title=\"Lighthouse.jpg\" alt=\"Lighthouse.jpg\"/><\/p>","note":"没有备注没有备注没有备注没有备注没有备注没有备注没有备注","status":1,"add_time":1524218091,"join_num":1}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * uid : 4
         * team_id : 1
         * team_name : 高密市志愿者协会
         * type : 1
         * title : 活动2
         * pic : http://leifeng.jzbwlkj.com/upload/default/20180420/5ef6a1a899b0ae144ac605504c429500.jpg
         * service_type : 3
         * join_time_s : 1524240000
         * join_time_e : 1524326400
         * start_time : 1524672000
         * end_time : 1524844800
         * service_time : 2天
         * service_hour : 3
         * service_num : 20
         * city_id : 1
         * address : 济南市天桥区
         * lat : 0.000000
         * lng : 0.000000
         * sign_scope : 0
         * canbu : 0
         * jiaotongbuzu : 0
         * baoxianbuzu : 0
         * peixun : 0
         * requirement : <p>活动要求这里带着图片<img src="http://leifeng.jzbwlkj.com/upload/default/20180420/29ce36843dbeb291f8ce6c5de2e85402.jpg" title="Lighthouse.jpg" alt="Lighthouse.jpg"/></p>
         * content : <p>活动详情这里也带着图片</p>
         * note : 没有相关岗位备注
         * status : 1
         * add_time : 1524271970
         * join_num : 1
         */

        private int id;
        private int uid;
        private int team_id;
        private String team_name;
        private int type;
        private String title;
        private String pic;
        private int service_type;
        private int join_time_s;
        private int join_time_e;
        private int start_time;
        private int end_time;
        private String service_time;
        private int service_hour;
        private int service_num;
        private int city_id;
        private String address;
        private String lat;
        private String lng;
        private int sign_scope;
        private int canbu;
        private int jiaotongbuzu;
        private int baoxianbuzu;
        private int peixun;
        private String requirement;
        private String content;
        private String note;
        private int status;
        private int add_time;
        private int join_num;

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

        public int getTeam_id() {
            return team_id;
        }

        public void setTeam_id(int team_id) {
            this.team_id = team_id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getService_type() {
            return service_type;
        }

        public void setService_type(int service_type) {
            this.service_type = service_type;
        }

        public int getJoin_time_s() {
            return join_time_s;
        }

        public void setJoin_time_s(int join_time_s) {
            this.join_time_s = join_time_s;
        }

        public int getJoin_time_e() {
            return join_time_e;
        }

        public void setJoin_time_e(int join_time_e) {
            this.join_time_e = join_time_e;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getService_time() {
            return service_time;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public int getService_hour() {
            return service_hour;
        }

        public void setService_hour(int service_hour) {
            this.service_hour = service_hour;
        }

        public int getService_num() {
            return service_num;
        }

        public void setService_num(int service_num) {
            this.service_num = service_num;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getSign_scope() {
            return sign_scope;
        }

        public void setSign_scope(int sign_scope) {
            this.sign_scope = sign_scope;
        }

        public int getCanbu() {
            return canbu;
        }

        public void setCanbu(int canbu) {
            this.canbu = canbu;
        }

        public int getJiaotongbuzu() {
            return jiaotongbuzu;
        }

        public void setJiaotongbuzu(int jiaotongbuzu) {
            this.jiaotongbuzu = jiaotongbuzu;
        }

        public int getBaoxianbuzu() {
            return baoxianbuzu;
        }

        public void setBaoxianbuzu(int baoxianbuzu) {
            this.baoxianbuzu = baoxianbuzu;
        }

        public int getPeixun() {
            return peixun;
        }

        public void setPeixun(int peixun) {
            this.peixun = peixun;
        }

        public String getRequirement() {
            return requirement;
        }

        public void setRequirement(String requirement) {
            this.requirement = requirement;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
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

        public int getJoin_num() {
            return join_num;
        }

        public void setJoin_num(int join_num) {
            this.join_num = join_num;
        }
    }

}

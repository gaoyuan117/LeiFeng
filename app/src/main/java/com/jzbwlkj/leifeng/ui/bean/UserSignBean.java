package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/9.
 */

public class UserSignBean {

    /**
     * total : 6
     * per_page : 15
     * current_page : 1
     * last_page : 1
     * data : [{"id":1,"uid":7,"activity_id":4,"time_s":1525741600,"lng_s":"36.696250","lat_s":"117.155612","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":null},{"id":2,"uid":7,"activity_id":4,"time_s":1525748889,"lng_s":"36.696250","lat_s":"117.155612","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":null},{"id":3,"uid":7,"activity_id":4,"time_s":1525749647,"lng_s":"36.696250","lat_s":"117.155612","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":null},{"id":4,"uid":7,"activity_id":4,"time_s":1526286401,"lng_s":"36.695898","lat_s":"117.155409","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":null},{"id":5,"uid":7,"activity_id":4,"time_s":1527864528,"lng_s":"36.637588","lat_s":"117.118106","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":null},{"id":6,"uid":7,"activity_id":6,"time_s":1528440929,"lng_s":"36.696110","lat_s":"117.155535","time_e":0,"lng_e":"0.000000","lat_e":"0.000000","service_hour":"0.00","is_normal":1,"manager_type":0,"manager_uid":0,"is_del":0,"activity_name":"扶贫活动一起来"}]
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
         * id : 1
         * uid : 7
         * activity_id : 4
         * time_s : 1525741600
         * lng_s : 36.696250
         * lat_s : 117.155612
         * time_e : 0
         * lng_e : 0.000000
         * lat_e : 0.000000
         * service_hour : 0.00
         * is_normal : 1
         * manager_type : 0
         * manager_uid : 0
         * is_del : 0
         * activity_name : null
         */

        private int id;
        private int uid;
        private int activity_id;
        private long time_s;
        private String lng_s;
        private String lat_s;
        private long time_e;
        private String lng_e;
        private String lat_e;
        private String service_hour;
        private int is_normal;
        private int manager_type;
        private int manager_uid;
        private int is_del;
        private String activity_name;

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

        public long getTime_s() {
            return time_s;
        }

        public void setTime_s(long time_s) {
            this.time_s = time_s;
        }

        public String getLng_s() {
            return lng_s;
        }

        public void setLng_s(String lng_s) {
            this.lng_s = lng_s;
        }

        public String getLat_s() {
            return lat_s;
        }

        public void setLat_s(String lat_s) {
            this.lat_s = lat_s;
        }

        public long getTime_e() {
            return time_e;
        }

        public void setTime_e(long time_e) {
            this.time_e = time_e;
        }

        public String getLng_e() {
            return lng_e;
        }

        public void setLng_e(String lng_e) {
            this.lng_e = lng_e;
        }

        public String getLat_e() {
            return lat_e;
        }

        public void setLat_e(String lat_e) {
            this.lat_e = lat_e;
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

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getActivity_name() {
            return activity_name;
        }

        public void setActivity_name(String activity_name) {
            this.activity_name = activity_name;
        }
    }
}

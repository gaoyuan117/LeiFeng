package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */

public class NewsBean {


    private List<AdInfoBean> ad_info;
    private List<NewsListBean> news_list;

    public List<AdInfoBean> getAd_info() {
        return ad_info;
    }

    public void setAd_info(List<AdInfoBean> ad_info) {
        this.ad_info = ad_info;
    }

    public List<NewsListBean> getNews_list() {
        return news_list;
    }

    public void setNews_list(List<NewsListBean> news_list) {
        this.news_list = news_list;
    }

    public static class AdInfoBean {
        /**
         * image : http://leifeng.jzbwlkj.com/upload/admin/20180622/297ccc5d57b169ed1bfabd54c7f8bf69.jpg
         * type : 1
         * url : 56
         */

        private String image;
        private int type;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class NewsListBean {
        /**
         * id : 59
         * type : 0
         * city_id : 0
         * title : 市总工会志愿者端午节环保公益活动
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180622/efe6b3f4400784e7b9d3bcf0a56b9fa3.jpg
         * desc : 市总工会在端午期间组织本单位党员在凤凰公园进行志愿服务活动。
         * content : <p><img src="http://leifeng.jzbwlkj.com/upload/default/20180622/c15cdeed1057201a2e0c56adae3b19e9.jpg" style="" title="3.jpg"/></p><p><img src="http://leifeng.jzbwlkj.com/upload/default/20180622/7abd0191e42a19aa55d187f7852e8ee0.jpg" style="" title="2.jpg"/></p><p><br/></p>
         * is_recommend : 0
         * add_time : 1529655122
         */

        private int id;
        private int type;
        private int city_id;
        private String title;
        private String pic;
        private String desc;
        private String content;
        private int is_recommend;
        private int add_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }
}

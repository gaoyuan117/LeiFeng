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
         * image : admin/20180410/7874cb041e4f84249237330f1c621ca7.jpg
         * url :
         */

        private String image;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
         * id : 1
         * type : 0
         * city_id : 0
         * title : 测试志愿新闻
         * pic : admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
         * desc : 时代发生地方
         * content : <p>是的根深蒂固大哥<br/></p>
         * is_recommend : 1
         * add_time : 0
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

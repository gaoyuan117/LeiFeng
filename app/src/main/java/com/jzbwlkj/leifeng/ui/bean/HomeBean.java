package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class HomeBean {

    /**
     * team_count : 8
     * user_count : 24
     * user_polital_count : 18
     * index_ad1 : [{"image":"http://p0.so.qhimgs1.com/bdr/_240_/t0115a541834ffb65fc.jpg","type":0,"url":"http://www.chinaleifeng.com/"},{"image":"http://hbimg.b0.upaiyun.com/fcb1c6113ec2f6d46bef2561747b230dd73f40335110c-6XaEme_fw658","type":0,"url":"http://hbimg.b0.upaiyun.com/fcb1c6113ec2f6d46bef2561747b230dd73f40335110c-6XaEme_fw658"}]
     * news_recommend_list : [{"id":5,"title":"剪纸\u201c走进\u201d养老院 八旬老人乐开怀","pic":"http://leifeng.jzbwlkj.com/upload/admin/20180603/219556ab97747ca8754986bb31ca6770.jpg"},{"id":4,"title":"高密市全国最美志愿者 助力全省乡村文明游","pic":"http://leifeng.jzbwlkj.com/upload/admin/20180604/487348c1f6b3aec5f56193fac3540c94.jpg"},{"id":1,"title":"高密市志愿者协会妇联积极响应市妇联\u201c巾帼美家\u201d的倡议","pic":"http://leifeng.jzbwlkj.com/upload/admin/20180604/cda37c168542cdd833881351cac3dd32.jpg"}]
     * new_message_num : 0
     */

    private int team_count;
    private int user_count;
    private int user_polital_count;
    private int new_message_num;
    private List<IndexAd1Bean> index_ad1;
    private List<NewsRecommendListBean> news_recommend_list;

    public int getTeam_count() {
        return team_count;
    }

    public void setTeam_count(int team_count) {
        this.team_count = team_count;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public int getUser_polital_count() {
        return user_polital_count;
    }

    public void setUser_polital_count(int user_polital_count) {
        this.user_polital_count = user_polital_count;
    }

    public int getNew_message_num() {
        return new_message_num;
    }

    public void setNew_message_num(int new_message_num) {
        this.new_message_num = new_message_num;
    }

    public List<IndexAd1Bean> getIndex_ad1() {
        return index_ad1;
    }

    public void setIndex_ad1(List<IndexAd1Bean> index_ad1) {
        this.index_ad1 = index_ad1;
    }

    public List<NewsRecommendListBean> getNews_recommend_list() {
        return news_recommend_list;
    }

    public void setNews_recommend_list(List<NewsRecommendListBean> news_recommend_list) {
        this.news_recommend_list = news_recommend_list;
    }

    public static class IndexAd1Bean {
        /**
         * image : http://p0.so.qhimgs1.com/bdr/_240_/t0115a541834ffb65fc.jpg
         * type : 0
         * url : http://www.chinaleifeng.com/
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

    public static class NewsRecommendListBean {
        /**
         * id : 5
         * title : 剪纸“走进”养老院 八旬老人乐开怀
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180603/219556ab97747ca8754986bb31ca6770.jpg
         */

        private int id;
        private String title;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}

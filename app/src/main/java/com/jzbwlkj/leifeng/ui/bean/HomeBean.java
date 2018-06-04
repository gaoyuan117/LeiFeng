package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class HomeBean {


    /**
     * team_count : 3
     * user_count : 6
     * user_polital_count : 6
     * index_ad1 : [{"image":"https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1527842328514&amp;di=4bc1a35f252b86b4ff97c38509a29a25&amp;imgtype=0&amp;src=http%3A%2F%2Fpic28.photophoto.cn%2F20130706%2F0007020138947291_b.jpg","url":"https://www.baidu.com/"}]
     * news_recommend_list : [{"id":5,"title":"剪纸\u201c走进\u201d养老院 八旬老人乐开怀","pic":"http://img1.cache.netease.com/catchpic/3/33/33F354A34F973B79319493AB61A11130.jpg"},{"id":4,"title":"高密市全国最美志愿者 助力全省乡村文明游","pic":"http://weifang.dzwww.com/gmxw/201804/W020180423581869362103.jpg"},{"id":1,"title":"高密市志愿者协会妇联积极响应市妇联\u201c巾帼美家\u201d的倡议","pic":"https://imgsa.baidu.com/forum/w%3D580/sign=52e936f98dcb39dbc1c0675ee01609a7/9e3c4c63f6246b60831ee067e7f81a4c510fa242.jpg"}]
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
         * image : https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1527842328514&amp;di=4bc1a35f252b86b4ff97c38509a29a25&amp;imgtype=0&amp;src=http%3A%2F%2Fpic28.photophoto.cn%2F20130706%2F0007020138947291_b.jpg
         * url : https://www.baidu.com/
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

    public static class NewsRecommendListBean {
        /**
         * id : 5
         * title : 剪纸“走进”养老院 八旬老人乐开怀
         * pic : http://img1.cache.netease.com/catchpic/3/33/33F354A34F973B79319493AB61A11130.jpg
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

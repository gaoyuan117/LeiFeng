package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class HomeBean {
    /**
     * team_count : 2
     * user_count : 5
     * user_polital_count : 5
     * index_ad1 : {"image":"http://leifeng.jzbwlkj.com/upload/admin/20180410/99fb82cf5fc990e05f848e79e3e13bc4.jpg","url":""}
     * news_recommend_list : [{"id":1,"title":"测试志愿新闻","pic":"http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg"}]
     * new_message_num : 0
     */

    private int team_count;
    private int user_count;
    private int user_polital_count;
    private IndexAd1Bean index_ad1;
    private int new_message_num;
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

    public IndexAd1Bean getIndex_ad1() {
        return index_ad1;
    }

    public void setIndex_ad1(IndexAd1Bean index_ad1) {
        this.index_ad1 = index_ad1;
    }

    public int getNew_message_num() {
        return new_message_num;
    }

    public void setNew_message_num(int new_message_num) {
        this.new_message_num = new_message_num;
    }

    public List<NewsRecommendListBean> getNews_recommend_list() {
        return news_recommend_list;
    }

    public void setNews_recommend_list(List<NewsRecommendListBean> news_recommend_list) {
        this.news_recommend_list = news_recommend_list;
    }

    public static class IndexAd1Bean {
        /**
         * image : http://leifeng.jzbwlkj.com/upload/admin/20180410/99fb82cf5fc990e05f848e79e3e13bc4.jpg
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

    public static class NewsRecommendListBean {
        /**
         * id : 1
         * title : 测试志愿新闻
         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
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

//    /**
//     * team_count : 1
//     * user_count : 1
//     * user_polital_count : 1
//     * index_ad1 : {"image":"http://leifeng.jzbwlkj.com/upload/admin/20180410/99fb82cf5fc990e05f848e79e3e13bc4.jpg","url":""}
//     * news_recommend_list : [{"id":1,"title":"测试志愿新闻","pic":"http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg"}]
//     */
//
//    private int team_count;
//    private int user_count;
//    private int user_polital_count;
//    private IndexAd1Bean index_ad1;
//    private List<NewsRecommendListBean> news_recommend_list;
//
//    public int getTeam_count() {
//        return team_count;
//    }
//
//    public void setTeam_count(int team_count) {
//        this.team_count = team_count;
//    }
//
//    public int getUser_count() {
//        return user_count;
//    }
//
//    public void setUser_count(int user_count) {
//        this.user_count = user_count;
//    }
//
//    public int getUser_polital_count() {
//        return user_polital_count;
//    }
//
//    public void setUser_polital_count(int user_polital_count) {
//        this.user_polital_count = user_polital_count;
//    }
//
//    public IndexAd1Bean getIndex_ad1() {
//        return index_ad1;
//    }
//
//    public void setIndex_ad1(IndexAd1Bean index_ad1) {
//        this.index_ad1 = index_ad1;
//    }
//
//    public List<NewsRecommendListBean> getNews_recommend_list() {
//        return news_recommend_list;
//    }
//
//    public void setNews_recommend_list(List<NewsRecommendListBean> news_recommend_list) {
//        this.news_recommend_list = news_recommend_list;
//    }
//
//    public static class IndexAd1Bean {
//        /**
//         * image : http://leifeng.jzbwlkj.com/upload/admin/20180410/99fb82cf5fc990e05f848e79e3e13bc4.jpg
//         * url :
//         */
//
//        private String image;
//        private String url;
//
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//    }
//
//    public static class NewsRecommendListBean {
//        /**
//         * id : 1
//         * title : 测试志愿新闻
//         * pic : http://leifeng.jzbwlkj.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
//         */
//
//        private int id;
//        private String title;
//        private String pic;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getPic() {
//            return pic;
//        }
//
//        public void setPic(String pic) {
//            this.pic = pic;
//        }
//    }


}

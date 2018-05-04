package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class RankBean {


    private List<RankUserBean> rank_user;
    private List<RankTeamBean> rank_team;

    public List<RankUserBean> getRank_user() {
        return rank_user;
    }

    public void setRank_user(List<RankUserBean> rank_user) {
        this.rank_user = rank_user;
    }

    public List<RankTeamBean> getRank_team() {
        return rank_team;
    }

    public void setRank_team(List<RankTeamBean> rank_team) {
        this.rank_team = rank_team;
    }

    public static class RankUserBean {
        /**
         * id : 4
         * user_nickname : 张三
         * avatar : admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
         * sex : 1
         * polital_status : 0
         * service_hour : 0.00
         */

        private int id;
        private String user_nickname;
        private String avatar;
        private int sex;
        private int polital_status;
        private String service_hour;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getPolital_status() {
            return polital_status;
        }

        public void setPolital_status(int polital_status) {
            this.polital_status = polital_status;
        }

        public String getService_hour() {
            return service_hour;
        }

        public void setService_hour(String service_hour) {
            this.service_hour = service_hour;
        }
    }

    public static class RankTeamBean {
        /**
         * id : 1
         * team_name : 高密市志愿者协会
         * pic : http://leifeng.test.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
         * service_hour : 0.00
         */

        private int id;
        private String team_name;
        private String pic;
        private String service_hour;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTeam_name() {
            return team_name;
        }

        public void setTeam_name(String team_name) {
            this.team_name = team_name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getService_hour() {
            return service_hour;
        }

        public void setService_hour(String service_hour) {
            this.service_hour = service_hour;
        }
    }
}

package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class JoinTeamListBean {

    /**
     * status_text : 审核中
     * list : [{"id":5,"uid":7,"team_id":1,"is_captain":0,"status":0,"note":"","add_time":1524103521,"audit_time":0,"team_name":"高密市志愿者协会","pic":"http://leifeng.test.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg","desc":"&lt;p&gt;时代发生地方的风格的风格的&lt;/p&gt;"}]
     */

    private String status_text;
    private List<ListBean> list;

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 5
         * uid : 7
         * team_id : 1
         * is_captain : 0
         * status : 0
         * note :
         * add_time : 1524103521
         * audit_time : 0
         * team_name : 高密市志愿者协会
         * pic : http://leifeng.test.com/upload/admin/20180331/5b657db4b37d382f71f1f3d16b47556a.jpg
         * desc : &lt;p&gt;时代发生地方的风格的风格的&lt;/p&gt;
         */

        private int id;
        private int uid;
        private int team_id;
        private int is_captain;
        private int status;
        private String note;
        private long add_time;
        private int audit_time;
        private String team_name;
        private String pic;
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

        public int getTeam_id() {
            return team_id;
        }

        public void setTeam_id(int team_id) {
            this.team_id = team_id;
        }

        public int getIs_captain() {
            return is_captain;
        }

        public void setIs_captain(int is_captain) {
            this.is_captain = is_captain;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(int audit_time) {
            this.audit_time = audit_time;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}


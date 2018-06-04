package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/5/31.
 */

public class TeamLoginBean {

    /**
     * team_id : 6
     * team_name : 我的队伍
     * pic : http://leifeng.jzbwlkj.com/upload/default/20180531/4197b464c5c34ef1b58d7eef006a074b.jpg
     * status : 1
     * team_token : c7b4c855313dbc64b5535a77fa4bd8e05484b9a9ea9f90c7acdd58bea977fb33
     */

    private int team_id;
    private String team_name;
    private String pic;
    private int status;
    private String team_token;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeam_token() {
        return team_token;
    }

    public void setTeam_token(String team_token) {
        this.team_token = team_token;
    }
}

package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/5/31.
 */

public class TeamLoginBean {

    /**
     * team_id : 6
     * team_name : 密水队伍
     * pic : http://leifeng.jzbwlkj.com/upload/default/20180605/ccb238fa14063668407c9e991c2ae075.jpg
     * status : 1
     * contact_mobile : 15628801370
     * sign_auth : 1
     * team_token : cdb1f918bd3e3c6be52de7607e879a9f6ebcfd7b436d80c441e05975ca4134ec
     */

    private int team_id;
    private String team_name;
    private String pic;
    private int status;
    private String contact_mobile;
    private int sign_auth;
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

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public int getSign_auth() {
        return sign_auth;
    }

    public void setSign_auth(int sign_auth) {
        this.sign_auth = sign_auth;
    }

    public String getTeam_token() {
        return team_token;
    }

    public void setTeam_token(String team_token) {
        this.team_token = team_token;
    }
}

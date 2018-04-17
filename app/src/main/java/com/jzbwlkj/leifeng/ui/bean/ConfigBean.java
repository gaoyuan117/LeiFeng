package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ConfigBean {


    /**
     * zhuceshouze : 凤城雷锋志愿服务APP注册志愿者服务守则
     * sex : ["保密","男","女"]
     * id_type : ["身份证","港澳台身份证","护照"]
     * polital_status : ["党员","团员","其他"]
     * education : ["小学","初中","高中","专科","本科","研究生","博士","博士后"]
     * natinal_list : [{"id":1,"name":"汉族"},{"id":2,"name":"蒙古族"},{"id":3,"name":"回族"},{"id":4,"name":"藏族"},{"id":5,"name":"维吾尔族"},{"id":6,"name":"苗族"},{"id":7,"name":"彝族"},{"id":8,"name":"壮族"},{"id":9,"name":"布依族"},{"id":10,"name":"朝鲜族"},{"id":11,"name":"满族"},{"id":12,"name":"侗族"},{"id":13,"name":"瑶族"},{"id":14,"name":"白族"},{"id":15,"name":"土家族"},{"id":16,"name":"哈尼族"},{"id":17,"name":"哈萨克族"},{"id":18,"name":"傣族"},{"id":19,"name":"黎族"},{"id":20,"name":"傈僳族"},{"id":21,"name":"佤族"},{"id":22,"name":"畲族"},{"id":23,"name":"高山族"},{"id":24,"name":"拉祜族"},{"id":25,"name":"水族"},{"id":26,"name":"东乡族"},{"id":27,"name":"纳西族"},{"id":28,"name":"景颇族"},{"id":29,"name":"柯尔克孜族"},{"id":30,"name":"土族"},{"id":31,"name":"达斡尔族"},{"id":32,"name":"仫佬族"},{"id":33,"name":"羌族"},{"id":34,"name":" 布朗族"},{"id":35,"name":" 撒拉族"},{"id":36,"name":" 毛难族"},{"id":37,"name":" 仡佬族"},{"id":38,"name":" 锡伯族"},{"id":39,"name":" 阿昌族"},{"id":40,"name":" 普米族"},{"id":41,"name":" 塔吉克族"},{"id":42,"name":" 怒族"},{"id":43,"name":" 乌孜别克族"},{"id":44,"name":" 俄罗斯族"},{"id":45,"name":" 鄂温克族"},{"id":46,"name":" 崩龙族"},{"id":47,"name":" 保安族"},{"id":48,"name":" 裕固族"},{"id":49,"name":" 京族"},{"id":50,"name":" 塔塔尔族"},{"id":51,"name":" 独龙族"},{"id":52,"name":" 鄂伦春族"},{"id":53,"name":" 赫哲族"},{"id":54,"name":" 门巴族"},{"id":55,"name":" 珞巴族"},{"id":56,"name":" 基诺族"},{"id":57,"name":" 其他"}]
     * job_list : [{"id":1,"name":"设计人员"}]
     * city_list : [{"id":1,"pid":0,"name":"高密市"},{"id":2,"pid":1,"name":"密水街道"}]
     */

    private String zhuceshouze;
    private List<String> sex;
    private List<String> id_type;
    private List<String> polital_status;
    private List<String> education;
    private List<NatinalListBean> natinal_list;
    private List<JobListBean> job_list;
    private List<CityListBean> city_list;

    public String getZhuceshouze() {
        return zhuceshouze;
    }

    public void setZhuceshouze(String zhuceshouze) {
        this.zhuceshouze = zhuceshouze;
    }

    public List<String> getSex() {
        return sex;
    }

    public void setSex(List<String> sex) {
        this.sex = sex;
    }

    public List<String> getId_type() {
        return id_type;
    }

    public void setId_type(List<String> id_type) {
        this.id_type = id_type;
    }

    public List<String> getPolital_status() {
        return polital_status;
    }

    public void setPolital_status(List<String> polital_status) {
        this.polital_status = polital_status;
    }

    public List<String> getEducation() {
        return education;
    }

    public void setEducation(List<String> education) {
        this.education = education;
    }

    public List<NatinalListBean> getNatinal_list() {
        return natinal_list;
    }

    public void setNatinal_list(List<NatinalListBean> natinal_list) {
        this.natinal_list = natinal_list;
    }

    public List<JobListBean> getJob_list() {
        return job_list;
    }

    public void setJob_list(List<JobListBean> job_list) {
        this.job_list = job_list;
    }

    public List<CityListBean> getCity_list() {
        return city_list;
    }

    public void setCity_list(List<CityListBean> city_list) {
        this.city_list = city_list;
    }

    public static class NatinalListBean {
        /**
         * id : 1
         * name : 汉族
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class JobListBean {
        /**
         * id : 1
         * name : 设计人员
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CityListBean {
        /**
         * id : 1
         * pid : 0
         * name : 高密市
         */

        private int id;
        private int pid;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

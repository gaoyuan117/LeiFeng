package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ConfigBean {

    /**
     * zhuceshouze : 凤城雷锋志愿服务APP注册志愿者服务守则
     * app_version : 1.0
     * app_url_android :
     * app_url_ios :
     * service_tel : 154232645
     * service_address : 中国山东
     * aboutus : <p>关于我们时代发生地方是</p>
     * aboutgoods : <p><span style="color: rgb(44, 62, 80); font-family: &quot;Source Sans Pro&quot;, Calibri, Candara, Arial, sans-serif; font-size: 14px; font-weight: bold; text-align: right; background-color: rgb(255, 255, 255);">义仓说明sdfsdfsd</span></p>
     * sex : ["保密","男","女"]
     * id_type : ["身份证","港澳台身份证","护照"]
     * polital_status : ["党员","团员","其他"]
     * education : ["小学","初中","高中","专科","本科","研究生","博士","博士后"]
     * service_type : ["赛会服务","助老助残","文体活动","儿童关怀","科普宣传","动物保护","生态环保","医疗卫生","社区服务","文化教育","扶贫帮困","安全生产","支教助学","邻里守望","应急救援","法律援助","其他"]
     * message_type : ["系统消息","培训通知","项目活动"]
     * goods_type : ["生活用品","学习用品","党务用品"]
     * service_time : ["每天","每周","每月","每年"]
     * sign_scope : ["300","500","1000","1500 "]
     * natinal_list : [{"id":1,"name":"汉族"},{"id":2,"name":"蒙古族"},{"id":3,"name":"回族"},{"id":4,"name":"藏族"},{"id":5,"name":"维吾尔族"},{"id":6,"name":"苗族"},{"id":7,"name":"彝族"},{"id":8,"name":"壮族"},{"id":9,"name":"布依族"},{"id":10,"name":"朝鲜族"},{"id":11,"name":"满族"},{"id":12,"name":"侗族"},{"id":13,"name":"瑶族"},{"id":14,"name":"白族"},{"id":15,"name":"土家族"},{"id":16,"name":"哈尼族"},{"id":17,"name":"哈萨克族"},{"id":18,"name":"傣族"},{"id":19,"name":"黎族"},{"id":20,"name":"傈僳族"},{"id":21,"name":"佤族"},{"id":22,"name":"畲族"},{"id":23,"name":"高山族"},{"id":24,"name":"拉祜族"},{"id":25,"name":"水族"},{"id":26,"name":"东乡族"},{"id":27,"name":"纳西族"},{"id":28,"name":"景颇族"},{"id":29,"name":"柯尔克孜族"},{"id":30,"name":"土族"},{"id":31,"name":"达斡尔族"},{"id":32,"name":"仫佬族"},{"id":33,"name":"羌族"},{"id":34,"name":" 布朗族"},{"id":35,"name":" 撒拉族"},{"id":36,"name":" 毛难族"},{"id":37,"name":" 仡佬族"},{"id":38,"name":" 锡伯族"},{"id":39,"name":" 阿昌族"},{"id":40,"name":" 普米族"},{"id":41,"name":" 塔吉克族"},{"id":42,"name":" 怒族"},{"id":43,"name":" 乌孜别克族"},{"id":44,"name":" 俄罗斯族"},{"id":45,"name":" 鄂温克族"},{"id":46,"name":" 崩龙族"},{"id":47,"name":" 保安族"},{"id":48,"name":" 裕固族"},{"id":49,"name":" 京族"},{"id":50,"name":" 塔塔尔族"},{"id":51,"name":" 独龙族"},{"id":52,"name":" 鄂伦春族"},{"id":53,"name":" 赫哲族"},{"id":54,"name":" 门巴族"},{"id":55,"name":" 珞巴族"},{"id":56,"name":" 基诺族"},{"id":57,"name":" 其他"}]
     * job_list : [{"id":1,"name":"设计人员"}]
     * city_list : [{"id":1,"pid":0,"name":"高密市"},{"id":2,"pid":1,"name":"密水街道"}]
     */

    private String zhuceshouze;
    private String app_version;
    private String app_url_android;
    private String app_url_ios;
    private String service_tel;
    private String service_address;
    private String aboutus;
    private String aboutgoods;
    private List<String> sex;
    private List<String> id_type;
    private List<String> polital_status;
    private List<String> education;
    private List<String> service_type;
    private List<String> message_type;
    private List<String> goods_type;
    private List<String> service_time;
    private List<String> sign_scope;
    private List<NatinalListBean> natinal_list;
    private List<JobListBean> job_list;
    private List<CityListBean> city_list;

    public String getZhuceshouze() {
        return zhuceshouze;
    }

    public void setZhuceshouze(String zhuceshouze) {
        this.zhuceshouze = zhuceshouze;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getApp_url_android() {
        return app_url_android;
    }

    public void setApp_url_android(String app_url_android) {
        this.app_url_android = app_url_android;
    }

    public String getApp_url_ios() {
        return app_url_ios;
    }

    public void setApp_url_ios(String app_url_ios) {
        this.app_url_ios = app_url_ios;
    }

    public String getService_tel() {
        return service_tel;
    }

    public void setService_tel(String service_tel) {
        this.service_tel = service_tel;
    }

    public String getService_address() {
        return service_address;
    }

    public void setService_address(String service_address) {
        this.service_address = service_address;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getAboutgoods() {
        return aboutgoods;
    }

    public void setAboutgoods(String aboutgoods) {
        this.aboutgoods = aboutgoods;
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

    public List<String> getService_type() {
        return service_type;
    }

    public void setService_type(List<String> service_type) {
        this.service_type = service_type;
    }

    public List<String> getMessage_type() {
        return message_type;
    }

    public void setMessage_type(List<String> message_type) {
        this.message_type = message_type;
    }

    public List<String> getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(List<String> goods_type) {
        this.goods_type = goods_type;
    }

    public List<String> getService_time() {
        return service_time;
    }

    public void setService_time(List<String> service_time) {
        this.service_time = service_time;
    }

    public List<String> getSign_scope() {
        return sign_scope;
    }

    public void setSign_scope(List<String> sign_scope) {
        this.sign_scope = sign_scope;
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

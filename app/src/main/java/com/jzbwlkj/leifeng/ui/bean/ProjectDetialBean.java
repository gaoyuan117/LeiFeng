package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class ProjectDetialBean {

    /**
     * id : 4
     * uid : 4
     * team_id : 1
     * team_name : 高密市志愿者协会
     * type : 1
     * title : 活动2
     * pic : http://leifeng.jzbwlkj.com/upload/default/20180420/5ef6a1a899b0ae144ac605504c429500.jpg
     * service_type : 3
     * join_time_s : 1524240000
     * join_time_e : 1524326400
     * start_time : 1524672000
     * end_time : 1524844800
     * service_time : 2天
     * service_hour : 3
     * service_num : 20
     * city_id : 1
     * address : 济南市天桥区
     * lat : 0.000000
     * lng : 0.000000
     * sign_scope : 0
     * canbu : 0
     * jiaotongbuzu : 0
     * baoxianbuzu : 0
     * peixun : 0
     * requirement : <p>活动要求这里带着图片<img src="http://leifeng.jzbwlkj.com/upload/default/20180420/29ce36843dbeb291f8ce6c5de2e85402.jpg" title="Lighthouse.jpg" alt="Lighthouse.jpg"/></p>
     * content : <p>活动详情这里也带着图片</p>
     * note : 没有相关岗位备注
     * status : 1
     * add_time : 1524271970
     * service_type_text : 儿童关怀
     * join_time_s_text : 2018-04-21
     * join_time_e_text : 2018-04-22
     * start_time_text : 2018-04-26
     * end_time_text : 2018-04-28
     * contact : 张三
     * contact_mobile : 12521542
     * log_info : null
     * praise_num : 2
     * is_praise : 1
     * join_info : {"status":1,"add_time":1524282085,"reason":""}
     * message_list : [{"id":5,"uid":7,"activity_id":4,"team_id":0,"pid":0,"content":"加一条留言","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524298413,"user_nickname":"孙常栋","avatar":"","reply_info":[]},{"id":3,"uid":7,"activity_id":4,"team_id":0,"pid":0,"content":"我在这里对留言做出评价","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524297561,"user_nickname":"孙常栋","avatar":"","reply_info":[{"id":8,"uid":8,"activity_id":4,"team_id":0,"pid":3,"content":"在此留言","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524556286,"user_nickname":"葛汝鑫","avatar":""},{"id":4,"uid":7,"activity_id":4,"team_id":0,"pid":3,"content":"继续对留言做出评价","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524297851,"user_nickname":"孙常栋","avatar":""}]},{"id":2,"uid":7,"activity_id":4,"team_id":0,"pid":0,"content":"我这里实在测试留言","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524297477,"user_nickname":"孙常栋","avatar":"","reply_info":[]},{"id":1,"uid":7,"activity_id":4,"team_id":0,"pid":0,"content":"我的留言","status":0,"is_sys":0,"is_anonymous":0,"add_time":1524282118,"user_nickname":"孙常栋","avatar":"","reply_info":[]}]
     */

    private int id;
    private int uid;
    private int team_id;
    private String team_name;
    private int type;
    private String title;
    private String pic;
    private int service_type;
    private int join_time_s;
    private int join_time_e;
    private int start_time;
    private int end_time;
    private String service_time;
    private int service_hour;
    private int service_num;
    private int city_id;
    private String address;
    private String lat;
    private String lng;
    private int sign_scope;
    private int canbu;
    private int jiaotongbuzu;
    private int baoxianbuzu;
    private int peixun;
    private String requirement;
    private String content;
    private String note;
    private int status;
    private int add_time;
    private String service_type_text;
    private String join_time_s_text;
    private String join_time_e_text;
    private String start_time_text;
    private String end_time_text;
    private String contact;
    private String contact_mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private Object log_info;
    private int praise_num;
    private int is_praise;
    private JoinInfoBean join_info;
    private List<MessageListBean> message_list;

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

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getService_type() {
        return service_type;
    }

    public void setService_type(int service_type) {
        this.service_type = service_type;
    }

    public int getJoin_time_s() {
        return join_time_s;
    }

    public void setJoin_time_s(int join_time_s) {
        this.join_time_s = join_time_s;
    }

    public int getJoin_time_e() {
        return join_time_e;
    }

    public void setJoin_time_e(int join_time_e) {
        this.join_time_e = join_time_e;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getService_time() {
        return service_time;
    }

    public void setService_time(String service_time) {
        this.service_time = service_time;
    }

    public int getService_hour() {
        return service_hour;
    }

    public void setService_hour(int service_hour) {
        this.service_hour = service_hour;
    }

    public int getService_num() {
        return service_num;
    }

    public void setService_num(int service_num) {
        this.service_num = service_num;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getSign_scope() {
        return sign_scope;
    }

    public void setSign_scope(int sign_scope) {
        this.sign_scope = sign_scope;
    }

    public int getCanbu() {
        return canbu;
    }

    public void setCanbu(int canbu) {
        this.canbu = canbu;
    }

    public int getJiaotongbuzu() {
        return jiaotongbuzu;
    }

    public void setJiaotongbuzu(int jiaotongbuzu) {
        this.jiaotongbuzu = jiaotongbuzu;
    }

    public int getBaoxianbuzu() {
        return baoxianbuzu;
    }

    public void setBaoxianbuzu(int baoxianbuzu) {
        this.baoxianbuzu = baoxianbuzu;
    }

    public int getPeixun() {
        return peixun;
    }

    public void setPeixun(int peixun) {
        this.peixun = peixun;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getService_type_text() {
        return service_type_text;
    }

    public void setService_type_text(String service_type_text) {
        this.service_type_text = service_type_text;
    }

    public String getJoin_time_s_text() {
        return join_time_s_text;
    }

    public void setJoin_time_s_text(String join_time_s_text) {
        this.join_time_s_text = join_time_s_text;
    }

    public String getJoin_time_e_text() {
        return join_time_e_text;
    }

    public void setJoin_time_e_text(String join_time_e_text) {
        this.join_time_e_text = join_time_e_text;
    }

    public String getStart_time_text() {
        return start_time_text;
    }

    public void setStart_time_text(String start_time_text) {
        this.start_time_text = start_time_text;
    }

    public String getEnd_time_text() {
        return end_time_text;
    }

    public void setEnd_time_text(String end_time_text) {
        this.end_time_text = end_time_text;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public Object getLog_info() {
        return log_info;
    }

    public void setLog_info(Object log_info) {
        this.log_info = log_info;
    }

    public int getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(int praise_num) {
        this.praise_num = praise_num;
    }

    public int getIs_praise() {
        return is_praise;
    }

    public void setIs_praise(int is_praise) {
        this.is_praise = is_praise;
    }

    public JoinInfoBean getJoin_info() {
        return join_info;
    }

    public void setJoin_info(JoinInfoBean join_info) {
        this.join_info = join_info;
    }

    public List<MessageListBean> getMessage_list() {
        return message_list;
    }

    public void setMessage_list(List<MessageListBean> message_list) {
        this.message_list = message_list;
    }

    public static class JoinInfoBean {
        /**
         * status : 1
         * add_time : 1524282085
         * reason :
         */

        private int status;
        private int add_time;
        private String reason;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class MessageListBean {
        /**
         * id : 5
         * uid : 7
         * activity_id : 4
         * team_id : 0
         * pid : 0
         * content : 加一条留言
         * status : 0
         * is_sys : 0
         * is_anonymous : 0
         * add_time : 1524298413
         * user_nickname : 孙常栋
         * avatar :
         * reply_info : []
         */

        private int id;
        private int uid;
        private int activity_id;
        private int team_id;
        private int pid;
        private String content;
        private int status;
        private int is_sys;
        private int is_anonymous;
        private int add_time;
        private String user_nickname;
        private String avatar;
        private List<ReplyInfoBean> reply_info;

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

        public int getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public int getTeam_id() {
            return team_id;
        }

        public void setTeam_id(int team_id) {
            this.team_id = team_id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_sys() {
            return is_sys;
        }

        public void setIs_sys(int is_sys) {
            this.is_sys = is_sys;
        }

        public int getIs_anonymous() {
            return is_anonymous;
        }

        public void setIs_anonymous(int is_anonymous) {
            this.is_anonymous = is_anonymous;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
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

        public List<ReplyInfoBean> getReply_info() {
            return reply_info;
        }

        public void setReply_info(List<ReplyInfoBean> reply_info) {
            this.reply_info = reply_info;
        }

        public static class ReplyInfoBean {
            /**
             * id : 4
             * uid : 7
             * activity_id : 4
             * pid : 3
             * content : 继续对留言做出评价
             * status : 0
             * is_sys : 0
             * is_anonymous : 0
             * add_time : 1524297851
             * user_nickname : 孙常栋
             * avatar :
             */

            private int id;
            private int uid;
            private int activity_id;
            private int pid;
            private String content;
            private int status;
            private int is_sys;
            private int is_anonymous;
            private int add_time;
            private String user_nickname;
            private String avatar;

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

            public int getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(int activity_id) {
                this.activity_id = activity_id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_sys() {
                return is_sys;
            }

            public void setIs_sys(int is_sys) {
                this.is_sys = is_sys;
            }

            public int getIs_anonymous() {
                return is_anonymous;
            }

            public void setIs_anonymous(int is_anonymous) {
                this.is_anonymous = is_anonymous;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
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
        }
    }

}

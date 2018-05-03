package com.jzbwlkj.leifeng.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class StudyBean {

    /**
     * id : 2
     * type : 2
     * city_id : 0
     * title : 我的学习园地
     * pic : http://leifeng.jzbwlkj.com/upload/admin/20180503/2dcec6fd2643dd6c01b915ca69991084.jpg
     * desc : 这里是学习园地
     * content : <p><span style=";font-family:宋体;font-size:16px">党的十九大是在全面建成小康社会决胜阶段、中国特色社会主义进入新时代的关键时期召开的一次十分重要的大会。我局坚持把学习宣传贯彻党的十九大精神作为当前和今后一个时期的首要政治任务来抓，切实把局党员干部的思想和行动统一到党的十九大精神上来，精心组织安排了各项学习宣传贯彻活动，营造了学习宣传贯彻党的十九大精神的浓厚氛围。</span></p><p><br/></p>
     * is_recommend : 0
     * add_time : 1525316390
     */

    private int id;
    private int type;
    private int city_id;
    private String title;
    private String pic;
    private String desc;
    private String content;
    private int is_recommend;
    private long add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(int is_recommend) {
        this.is_recommend = is_recommend;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

}

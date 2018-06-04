package com.jzbwlkj.leifeng.ui.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2018/4/23.
 */
@Entity
public class MySelfModel {
    @Id(autoincrement = true)
    private Long doctor_id;
    private String name;
    private String id;

    public MySelfModel() {
    }
    @Generated(hash = 76496079)
    public MySelfModel(Long doctor_id, String name, String id, int num, String pid,
            boolean selected) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.id = id;
        this.num = num;
        this.pid = pid;
        this.selected = selected;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private int num;
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    private String pid;
    private boolean selected;
    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Long getDoctor_id() {
        return this.doctor_id;
    }
    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }
}

package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MySelfModel {
    private String name;
    private String id;
    private boolean selected;
    public boolean isSelected() {
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
}

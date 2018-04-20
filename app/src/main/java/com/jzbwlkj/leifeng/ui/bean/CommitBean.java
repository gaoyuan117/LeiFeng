package com.jzbwlkj.leifeng.ui.bean;

/**
 * Created by Administrator on 2018/4/20.
 * 这个可以作为所有的提交返回结果的实体类
 */

public class CommitBean {

    /**
     * code : 200
     * message : 操作成功
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

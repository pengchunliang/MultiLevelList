package com.pcl.myapplication.bean;


public class Response {

    private Integer code;

    private String msg;

    private Depts data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Depts getData() {
        return data;
    }

    public void setData(Depts data) {
        this.data = data;
    }
}

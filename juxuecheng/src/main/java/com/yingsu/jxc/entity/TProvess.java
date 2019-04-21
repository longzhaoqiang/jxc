package com.yingsu.jxc.entity;

public class TProvess {
    private Integer id;

    private String provessNo;

    private String provessName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvessNo() {
        return provessNo;
    }

    public void setProvessNo(String provessNo) {
        this.provessNo = provessNo == null ? null : provessNo.trim();
    }

    public String getProvessName() {
        return provessName;
    }

    public void setProvessName(String provessName) {
        this.provessName = provessName == null ? null : provessName.trim();
    }
}
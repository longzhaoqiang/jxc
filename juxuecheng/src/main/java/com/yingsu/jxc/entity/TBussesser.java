package com.yingsu.jxc.entity;

import java.util.Date;

public class TBussesser {
    private Integer id;

    private Integer userId;

    private String bussName;

    private String bussType;

    private String address;

    private String contractor;

    private String phone;

    private String tearchpower;

    private String bussIdea;

    private String bussIntroduce;

    private String picId;

    private String wechatCode;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBussName() {
        return bussName;
    }

    public void setBussName(String bussName) {
        this.bussName = bussName == null ? null : bussName.trim();
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType == null ? null : bussType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor == null ? null : contractor.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTearchpower() {
        return tearchpower;
    }

    public void setTearchpower(String tearchpower) {
        this.tearchpower = tearchpower == null ? null : tearchpower.trim();
    }

    public String getBussIdea() {
        return bussIdea;
    }

    public void setBussIdea(String bussIdea) {
        this.bussIdea = bussIdea == null ? null : bussIdea.trim();
    }

    public String getBussIntroduce() {
        return bussIntroduce;
    }

    public void setBussIntroduce(String bussIntroduce) {
        this.bussIntroduce = bussIntroduce == null ? null : bussIntroduce.trim();
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId == null ? null : picId.trim();
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode == null ? null : wechatCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
package com.yingsu.jxc.entity;

import java.util.Date;

public class TRecruit {
    private Integer id;

    private Integer bussesserId;

    private String recruitTitle;

    private String recruitAddress;

    private String experence;

    private String recruitSalary;

    private String wxNumber;

    private String education;

    private String postInfo;

    private String recriutDemand;

    private String recriutRemark;

    private String recruitTime;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBussesserId() {
        return bussesserId;
    }

    public void setBussesserId(Integer bussesserId) {
        this.bussesserId = bussesserId;
    }

    public String getRecruitTitle() {
        return recruitTitle;
    }

    public void setRecruitTitle(String recruitTitle) {
        this.recruitTitle = recruitTitle == null ? null : recruitTitle.trim();
    }

    public String getRecruitAddress() {
        return recruitAddress;
    }

    public void setRecruitAddress(String recruitAddress) {
        this.recruitAddress = recruitAddress == null ? null : recruitAddress.trim();
    }

    public String getExperence() {
        return experence;
    }

    public void setExperence(String experence) {
        this.experence = experence == null ? null : experence.trim();
    }

    public String getRecruitSalary() {
        return recruitSalary;
    }

    public void setRecruitSalary(String recruitSalary) {
        this.recruitSalary = recruitSalary == null ? null : recruitSalary.trim();
    }

    public String getWxNumber() {
        return wxNumber;
    }

    public void setWxNumber(String wxNumber) {
        this.wxNumber = wxNumber == null ? null : wxNumber.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo == null ? null : postInfo.trim();
    }

    public String getRecriutDemand() {
        return recriutDemand;
    }

    public void setRecriutDemand(String recriutDemand) {
        this.recriutDemand = recriutDemand == null ? null : recriutDemand.trim();
    }

    public String getRecriutRemark() {
        return recriutRemark;
    }

    public void setRecriutRemark(String recriutRemark) {
        this.recriutRemark = recriutRemark == null ? null : recriutRemark.trim();
    }

    public String getRecruitTime() {
        return recruitTime;
    }

    public void setRecruitTime(String recruitTime) {
        this.recruitTime = recruitTime == null ? null : recruitTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
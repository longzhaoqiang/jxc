package com.yingsu.jxc.entity;

import java.util.Date;

public class TTeacher {
    private Integer id;

    private String teacherName;

    private String teacherIntroduce;

    private String mobile;

    private Integer bussesserId;

    private String teacherSubject;

    private Integer teachDate;

    private String teacherLogo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherIntroduce() {
        return teacherIntroduce;
    }

    public void setTeacherIntroduce(String teacherIntroduce) {
        this.teacherIntroduce = teacherIntroduce == null ? null : teacherIntroduce.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getBussesserId() {
        return bussesserId;
    }

    public void setBussesserId(Integer bussesserId) {
        this.bussesserId = bussesserId;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public Integer getTeachDate() {
        return teachDate;
    }

    public void setTeachDate(Integer teachDate) {
        this.teachDate = teachDate;
    }

    public String getTeacherLogo() {
        return teacherLogo;
    }

    public void setTeacherLogo(String teacherLogo) {
        this.teacherLogo = teacherLogo == null ? null : teacherLogo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
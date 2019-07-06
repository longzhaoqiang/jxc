package com.yingsu.jxc.entity;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value="TUserPlan对象", description="")
public class TCourse {
    private Integer id;

    private String courseName;

    private Integer bussesserId;

    private String titalInfo;

    private String cycleMumber;

    private Double courseFee;

    private String courseIntraduce;

    private String applyStudent;

    private String courseGoal;

    private String courseContent;

    private String courseSpecial;

    private Integer courseStata;

    private Date startTime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getBussesserId() {
        return bussesserId;
    }

    public void setBussesserId(Integer bussesserId) {
        this.bussesserId = bussesserId;
    }

    public String getTitalInfo() {
        return titalInfo;
    }

    public void setTitalInfo(String titalInfo) {
        this.titalInfo = titalInfo;
    }

    public String getCycleMumber() {
        return cycleMumber;
    }

    public void setCycleMumber(String cycleMumber) {
        this.cycleMumber = cycleMumber == null ? null : cycleMumber.trim();
    }

    public Double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(Double courseFee) {
        this.courseFee = courseFee;
    }

    public String getCourseIntraduce() {
        return courseIntraduce;
    }

    public void setCourseIntraduce(String courseIntraduce) {
        this.courseIntraduce = courseIntraduce == null ? null : courseIntraduce.trim();
    }

    public String getApplyStudent() {
        return applyStudent;
    }

    public void setApplyStudent(String applyStudent) {
        this.applyStudent = applyStudent == null ? null : applyStudent.trim();
    }

    public String getCourseGoal() {
        return courseGoal;
    }

    public void setCourseGoal(String courseGoal) {
        this.courseGoal = courseGoal == null ? null : courseGoal.trim();
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent == null ? null : courseContent.trim();
    }

    public String getCourseSpecial() {
        return courseSpecial;
    }

    public void setCourseSpecial(String courseSpecial) {
        this.courseSpecial = courseSpecial == null ? null : courseSpecial.trim();
    }

    public Integer getCourseStata() {
        return courseStata;
    }

    public void setCourseStata(Integer courseStata) {
        this.courseStata = courseStata;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
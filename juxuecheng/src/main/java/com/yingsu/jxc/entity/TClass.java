package com.yingsu.jxc.entity;

public class TClass {
    private Integer id;

    private String className;

    private String classYpe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassYpe() {
        return classYpe;
    }

    public void setClassYpe(String classYpe) {
        this.classYpe = classYpe == null ? null : classYpe.trim();
    }
}
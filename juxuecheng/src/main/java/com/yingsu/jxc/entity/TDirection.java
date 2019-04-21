package com.yingsu.jxc.entity;

public class TDirection {
    private Integer id;

    private String directionNo;

    private String directionName;

    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirectionNo() {
        return directionNo;
    }

    public void setDirectionNo(String directionNo) {
        this.directionNo = directionNo == null ? null : directionNo.trim();
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName == null ? null : directionName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
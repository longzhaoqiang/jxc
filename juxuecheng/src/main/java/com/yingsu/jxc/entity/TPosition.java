package com.yingsu.jxc.entity;

public class TPosition {
    private Integer id;

    private String directionNo;

    private String positionName;

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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }
}
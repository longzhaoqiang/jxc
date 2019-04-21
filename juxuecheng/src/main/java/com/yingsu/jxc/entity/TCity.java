package com.yingsu.jxc.entity;

public class TCity {
    private Integer id;

    private String cityNo;

    private String cityName;

    private Integer provessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getProvessId() {
        return provessId;
    }

    public void setProvessId(Integer provessId) {
        this.provessId = provessId;
    }
}
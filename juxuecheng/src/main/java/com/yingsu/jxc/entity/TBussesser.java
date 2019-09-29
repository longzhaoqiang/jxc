package com.yingsu.jxc.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private String wechat;

    private String picUrl;

    private String wechatCode;

    private Date createTime;
}
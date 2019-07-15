package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.TWeixinLogin;

public interface IWxService {

    /**
     * 查找用户
     * @param opinId
     * @return
     */
    TWeixinLogin getWeixinUser(String opinId);

    /**
     * 插入用户
     */
    Integer addWeixinUser(TWeixinLogin weixinLogin);
}

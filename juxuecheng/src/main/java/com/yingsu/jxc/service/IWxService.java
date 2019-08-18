package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.TWeixinLogin;
import com.yingsu.jxc.entity.TWxShare;

public interface IWxService {

    /**
     * 查找用户
     * @param opinId
     * @return
     */
    TWeixinLogin getWeixinUser(String opinId);

    /**
     * 查找微信分享信息
     * @param bussId
     * @return
     */
    TWxShare getWxShare(Integer bussId);

    /**
     * 插入用户
     */
    Integer addWeixinUser(TWeixinLogin weixinLogin);
}

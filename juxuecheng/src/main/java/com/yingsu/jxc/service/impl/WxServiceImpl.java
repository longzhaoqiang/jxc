package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TWeixinLogin;
import com.yingsu.jxc.mapper.TWeixinLoginMapper;
import com.yingsu.jxc.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements IWxService {

    @Autowired
    private TWeixinLoginMapper weixinLoginMapper;

    /**
     * 查找用户
     * @param opinId
     * @return
     */
    @Override
    public TWeixinLogin getWeixinUser(String opinId) {
        TWeixinLogin weixinLogin = weixinLoginMapper.selectByOpenId(opinId);
        return weixinLogin;
    }

    /**
     * 插入微信用户
     * @param weixinLogin
     * @return
     */
    @Override
    public Integer addWeixinUser(TWeixinLogin weixinLogin) {
        int result = weixinLoginMapper.insertSelective(weixinLogin);
        return result;
    }
}

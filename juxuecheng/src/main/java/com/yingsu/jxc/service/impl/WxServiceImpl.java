package com.yingsu.jxc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingsu.jxc.entity.TWeixinLogin;
import com.yingsu.jxc.entity.TWxShare;
import com.yingsu.jxc.mapper.TWeixinLoginMapper;
import com.yingsu.jxc.mapper.TWxShareMapper;
import com.yingsu.jxc.service.IWxService;

@Service
public class WxServiceImpl implements IWxService {

    @Autowired
    private TWeixinLoginMapper weixinLoginMapper;

    @Autowired
    private TWxShareMapper wxShareMapper;

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
     * 查询微信分享信息
     * @param bussId
     * @return
     */
    @Override
    public TWxShare getWxShare(Integer bussId){
        TWxShare wxShare = wxShareMapper.selectByPrimaryKey(bussId);
        return wxShare;
    }

    /**
     * 设置微信分享
     * @param wxShare
     * @return
     */
    @Override
    public Integer setWxShare(TWxShare wxShare) {
        Integer bussId = wxShare.getBussId();
        TWxShare tWxShare = wxShareMapper.selectByPrimaryKey(bussId);
        int result = 0;
        if (tWxShare == null) {
            result = wxShareMapper.insertSelective(wxShare);
        } else {
            wxShare.setId(tWxShare.getId());
            result = wxShareMapper.updateByPrimaryKeySelective(wxShare);
        }
        return result;
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

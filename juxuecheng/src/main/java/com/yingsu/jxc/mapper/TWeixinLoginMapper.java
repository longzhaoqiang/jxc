package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TWeixinLogin;

public interface TWeixinLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWeixinLogin record);

    int insertSelective(TWeixinLogin record);

    TWeixinLogin selectByOpenId(String openId);

    int updateByPrimaryKeySelective(TWeixinLogin record);

    int updateByPrimaryKey(TWeixinLogin record);
}
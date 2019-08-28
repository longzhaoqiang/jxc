package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TWxShare;

public interface TWxShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWxShare record);

    int insertSelective(TWxShare record);

    TWxShare selectByPrimaryKey(Integer bussId);

    int updateByPrimaryKeySelective(TWxShare record);

    int updateByPrimaryKey(TWxShare record);
}
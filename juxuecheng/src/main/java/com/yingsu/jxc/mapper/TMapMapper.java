package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TMap;

public interface TMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMap record);

    int insertSelective(TMap record);

    TMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMap record);

    int updateByPrimaryKey(TMap record);
}
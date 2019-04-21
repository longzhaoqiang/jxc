package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TDirection;

public interface TDirectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDirection record);

    int insertSelective(TDirection record);

    TDirection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDirection record);

    int updateByPrimaryKey(TDirection record);
}
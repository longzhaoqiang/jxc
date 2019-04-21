package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TProvess;

public interface TProvessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProvess record);

    int insertSelective(TProvess record);

    TProvess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TProvess record);

    int updateByPrimaryKey(TProvess record);
}
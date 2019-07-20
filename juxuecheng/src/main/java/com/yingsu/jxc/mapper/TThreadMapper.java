package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TThread;

public interface TThreadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TThread record);

    int insertSelective(TThread record);

    TThread selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TThread record);

    int updateByPrimaryKey(TThread record);
}
package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TClass;

public interface TClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClass record);

    int insertSelective(TClass record);

    TClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClass record);

    int updateByPrimaryKey(TClass record);
}
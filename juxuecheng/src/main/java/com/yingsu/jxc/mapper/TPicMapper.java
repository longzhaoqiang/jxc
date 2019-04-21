package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TPic;

public interface TPicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPic record);

    int insertSelective(TPic record);

    TPic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPic record);

    int updateByPrimaryKey(TPic record);
}
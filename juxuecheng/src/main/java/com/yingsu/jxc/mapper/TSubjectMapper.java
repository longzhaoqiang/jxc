package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TSubject;

public interface TSubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSubject record);

    int insertSelective(TSubject record);

    TSubject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TSubject record);

    int updateByPrimaryKey(TSubject record);
}
package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TRecruit;

public interface TRecruitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRecruit record);

    int insertSelective(TRecruit record);

    TRecruit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRecruit record);

    int updateByPrimaryKey(TRecruit record);
}
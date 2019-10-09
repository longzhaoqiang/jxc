package com.yingsu.jxc.mapper;

import java.util.List;

import com.yingsu.jxc.entity.TRecruit;

public interface TRecruitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRecruit record);

    int insertSelective(TRecruit record);

    List<TRecruit> selectList(Integer bussId);

    TRecruit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRecruit record);

    int updateByPrimaryKey(TRecruit record);
}
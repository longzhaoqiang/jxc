package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TAdviceWord;

import java.util.List;

public interface TAdviceWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdviceWord record);

    int insertSelective(TAdviceWord record);

    TAdviceWord selectByPrimaryKey(Integer id);

    List<TAdviceWord> selectListByBussId(Integer bussId);

    int updateByPrimaryKeySelective(TAdviceWord record);

    int updateByPrimaryKey(TAdviceWord record);
}
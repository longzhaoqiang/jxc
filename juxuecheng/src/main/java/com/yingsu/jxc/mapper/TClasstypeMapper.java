package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TClasstype;

import java.util.List;

public interface TClasstypeMapper {

    /**
     * 查找教育类型列表
     * @return
     */
    List<TClasstype> findClassTypeList();

    int deleteByPrimaryKey(Integer id);

    int insert(TClasstype record);

    int insertSelective(TClasstype record);

    TClasstype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClasstype record);

    int updateByPrimaryKey(TClasstype record);
}
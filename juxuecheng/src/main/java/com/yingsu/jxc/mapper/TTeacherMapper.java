package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TTeacher;

public interface TTeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTeacher record);

    int insertSelective(TTeacher record);

    TTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeacher record);

    int updateByPrimaryKey(TTeacher record);
}
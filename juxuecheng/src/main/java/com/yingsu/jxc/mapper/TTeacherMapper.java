package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TTeacher;

import java.util.List;

public interface TTeacherMapper {

    /**
     * 查找教师列表
     * @param bussesserId
     * @return
     */
    List<TTeacher> selectTeacherList(Integer bussesserId);

    int deleteByPrimaryKey(Integer id);

    int insert(TTeacher record);

    int insertSelective(TTeacher record);

    TTeacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeacher record);

    int updateByPrimaryKey(TTeacher record);
}
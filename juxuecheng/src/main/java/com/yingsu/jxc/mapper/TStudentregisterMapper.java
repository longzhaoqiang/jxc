package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TStudentregister;

public interface TStudentregisterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TStudentregister record);

    int insertSelective(TStudentregister record);

    TStudentregister selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TStudentregister record);

    int updateByPrimaryKey(TStudentregister record);
}
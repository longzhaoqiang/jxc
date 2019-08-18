package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TCourse;

import java.util.List;

public interface TCourseMapper {

    /**
     * 查找课程列表
     * @param id
     * @return
     */
    List<TCourse> findList(Integer bussId,Integer courseType);

    int deleteByPrimaryKey(Integer id);

    int insert(TCourse record);

    int insertSelective(TCourse record);

    TCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCourse record);

    int updateByPrimaryKey(TCourse record);
}
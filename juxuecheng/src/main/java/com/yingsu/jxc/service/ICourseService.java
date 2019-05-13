package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TCourse;

public interface ICourseService {

    /**
     * 获取课程列表
     * @param bussId
     * @return
     */
    public ResultBody getList(Integer bussId);

    /**
     * 添加课程
     * @param course
     * @return
     */
    public Integer addCourse(TCourse course);
}
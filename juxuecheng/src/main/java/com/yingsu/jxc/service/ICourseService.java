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

    /**
     * 查找详情
     * @param courseId
     * @return
     */
    public ResultBody getInfo(Integer courseId);

    /**
     * 删除
     * @param courseId
     * @return
     */
    public Integer delete(Integer courseId);

    /**
     * 修改
     * @param course
     * @return
     */
    public Integer update(TCourse course);

}

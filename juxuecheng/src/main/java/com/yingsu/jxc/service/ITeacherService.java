package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;

public interface ITeacherService {

    /**
     * 获取教师列表
     * @param bussesserId
     * @return
     */
    ResultBody getTeacherList(Integer bussesserId);

    /**
     * 获取教师
     * @param teacherId
     * @return
     */
    ResultBody getTeacher(Integer teacherId);

    /**
     * 添加教师
     * @param tTeacher
     * @return
     */
    ResultBody addTeacher(TTeacher tTeacher);

    /**
     * 删除教师
     * @param id
     * @return
     */
    ResultBody deleteTeacher(Integer id);
}

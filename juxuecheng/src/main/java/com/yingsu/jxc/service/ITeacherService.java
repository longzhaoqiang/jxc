package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;

public interface ITeacherService {

    /**
     * 添加教师
     * @param tTeacher
     * @return
     */
    ResultBody addTeacher(TTeacher tTeacher);
}

package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.mapper.TTeacherMapper;
import com.yingsu.jxc.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    TTeacherMapper teacherMapper;

    @Override
    public ResultBody addTeacher(TTeacher teacher) {
        ResultBody resultBody = new ResultBody();
        Integer result = teacherMapper.insertSelective(teacher);
        resultBody.setResultCode(result);
        return resultBody;
    }
}

package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.mapper.TTeacherMapper;
import com.yingsu.jxc.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    TTeacherMapper teacherMapper;

    /**
     * 获取教师列表
     * @param bussesserId
     * @return
     */
    @Override
    public ResultBody getTeacherList(Integer bussesserId) {
        ResultBody resultBody = new ResultBody();
        List<TTeacher> teachers = teacherMapper.selectTeacherList(bussesserId);
        if (teachers.size() > 0){
            resultBody.setResult(teachers);
        } else {
            resultBody.setResultCode(0);
            resultBody.setResultMsg("你还没有添加任何教师信息");
        }
        return resultBody;
    }

    /**
     * 获取教师信息
     * @param teacherId
     * @return
     */
    @Override
    public ResultBody getTeacher(Integer teacherId) {
        ResultBody resultBody = new ResultBody();
        TTeacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        resultBody.setResult(teacher);
        return resultBody;
    }

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @Override
    public ResultBody addTeacher(TTeacher teacher) {
        ResultBody resultBody = new ResultBody();
        Integer result = teacherMapper.insertSelective(teacher);
        resultBody.setResultCode(result);
        return resultBody;
    }
}

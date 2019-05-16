package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TCourse;
import com.yingsu.jxc.mapper.TCourseMapper;
import com.yingsu.jxc.service.ICourseService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private TCourseMapper courseMapper;

    @Override
    public ResultBody getList(Integer bussId) {
        ResultBody resultBody = new ResultBody();
        List<TCourse> courseList = courseMapper.findList(bussId);
        resultBody.setResult(courseList);
        return resultBody;
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @Override
    public Integer addCourse(TCourse course) {
        Integer result = courseMapper.insertSelective(course);
        return result;
    }

    /**
     * 查找详情
     * @param courseId
     * @return
     */
    @Override
    public ResultBody getInfo(Integer courseId) {
        ResultBody result = new ResultBody();
        TCourse course = courseMapper.selectByPrimaryKey(courseId);
        if (course == null){
            result.setResultCode(Constant.ERROR_CODE);
            result.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        result.setResult(course);
        return result;
    }
}

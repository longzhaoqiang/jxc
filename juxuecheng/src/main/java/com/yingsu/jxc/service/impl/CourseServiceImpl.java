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
    public ResultBody getList(Integer bussId,Integer courseType) {
        ResultBody resultBody = new ResultBody();
        List<TCourse> courseList = courseMapper.findList(bussId,courseType);
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

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @Override
    public Integer delete(Integer courseId) {
        Integer result = courseMapper.deleteByPrimaryKey(courseId);
        return result;
    }

    /**
     * 修改
     * @param course
     * @return
     */
    @Override
    public Integer update(TCourse course) {
        Integer result = courseMapper.updateByPrimaryKeySelective(course);
        return result;
    }
}

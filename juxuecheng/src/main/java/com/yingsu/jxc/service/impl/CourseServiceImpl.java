package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TCourse;
import com.yingsu.jxc.mapper.TCourseMapper;
import com.yingsu.jxc.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements ICourseService{

    @Autowired
    private TCourseMapper courseMapper;
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
}

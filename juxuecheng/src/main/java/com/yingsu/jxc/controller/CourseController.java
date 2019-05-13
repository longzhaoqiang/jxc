package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TCourse;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.ICourseService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/getList")
    @ResponseBody
    public ResultBody getList(Integer bussId){
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = courseService.getList(bussId);
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    @RequestMapping("/addCourse")
    @ResponseBody
    public ResultBody addCourse(HttpSession session, TCourse course){
        ResultBody resultBody = new ResultBody();
        try {
            TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
            Integer bussId = user.getBussesserId();
            course.setBussesserId(bussId);
            Integer result = courseService.addCourse(course);
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }
}

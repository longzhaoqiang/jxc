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
import org.springframework.web.servlet.ModelAndView;

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

    // 跳转到課程页面
    @RequestMapping("/getCourse")
    public ModelAndView hello(String courseId){
        ModelAndView mv = new ModelAndView();
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = courseService.getInfo(Integer.parseInt(courseId));
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        TCourse course = (TCourse) resultBody.getResult();
        mv.addObject("courseName",course.getCourseName());
        mv.addObject("titalInfo",course.getTitalInfo());
        mv.addObject("courseFee",course.getCourseFee());
        mv.addObject("courseIntraduce",course.getCourseIntraduce());
        mv.addObject("applyStudent",course.getApplyStudent());
        mv.addObject("courseGoal",course.getCourseGoal());
        mv.addObject("courseContent",course.getCourseContent());
        mv.addObject("courseSpecial",course.getCourseSpecial());
        mv.setViewName("course_info");
        return mv;
    }
}

package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TCourse;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.mapper.TUserMapper;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.service.ICourseService;
import com.yingsu.jxc.service.IUserService;
import com.yingsu.jxc.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Api(tags = {"课程"}, description = "课程")
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IBussesserService bussesserService;

    @RequestMapping("/getList")
    @ResponseBody
    public ResultBody getList(Integer bussId) {
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = courseService.getList(bussId);
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    // 添加课程
    @RequestMapping("/addCourse")
    @ResponseBody
    public ResultBody addCourse(Integer bussId, TCourse course) {
        ResultBody resultBody = new ResultBody();
        try {
            course.setBussesserId(bussId);
            Integer result = courseService.addCourse(course);
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    // 跳转到課程页面
    @RequestMapping("/getCourse")
    public ModelAndView hello(String param1, String flag) {
        ModelAndView mv = new ModelAndView();
        ResultBody resultBody = new ResultBody();
        try {
            resultBody = courseService.getInfo(Integer.parseInt(param1));
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        TCourse course = (TCourse) resultBody.getResult();
        mv.addObject("courseId", course.getId());
        mv.addObject("courseName", course.getCourseName());
        mv.addObject("titalInfo", course.getTitalInfo());
        mv.addObject("courseFee", course.getCourseFee());
        mv.addObject("courseIntraduce", course.getCourseIntraduce());
        mv.addObject("applyStudent", course.getApplyStudent());
        mv.addObject("courseGoal", course.getCourseGoal());
        mv.addObject("courseContent", course.getCourseContent());
        mv.addObject("courseSpecial", course.getCourseSpecial());
        // 修改时
        if ("1".equals(flag)) {
            mv.setViewName("course_update");
            return mv;
        }
        mv.setViewName("course_info");
        return mv;
    }

    // 删除课程
    @RequestMapping("/delete")
    @ResponseBody
    public ResultBody delete(String id) {
        ResultBody resultBody = new ResultBody();
        try {
            Integer result = courseService.delete(Integer.parseInt(id));
            resultBody.setResultCode(result);
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    // 修改课程
    @RequestMapping("/update")
    @ResponseBody
    public ResultBody delete(TCourse course) {
        ResultBody resultBody = new ResultBody();
        try {
            Integer result = courseService.update(course);
            resultBody.setResultCode(result);
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    @ApiOperation(value = "新增用户" ,  notes="新增注册")
    @PostMapping("/add" )
    @ResponseBody
    public ResultBody addcourse1(Integer uid, TCourse tCourse) {
        ResultBody resultBody = new ResultBody();

        try {
            TBussesser bussesser = bussesserService.getBussInfo(uid);
            if (bussesser != null) {
                Integer bussesssid = bussesser.getId();
                tCourse.setBussesserId(bussesssid);
                Integer relsut = courseService.addCourse(tCourse);
            }
        } catch (Exception e) {
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }
}

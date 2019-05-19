package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.util.Constant;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * 页面跳转Controller
 */
@Controller
@EnableAutoConfiguration
public class IndexController {

    // 首次进来时跳到页面
    @RequestMapping("/")
    public String index(){
        return "home";
    }

    // 跳转到我的页面
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    // 跳转到商家页面
    @RequestMapping("/buss")
    public String buss(){
        return "bussesserIndex";
    }

    // 跳转到商家添加页面
    @RequestMapping("/buss_add")
    public String buss_add(){
        return "buss_add";
    }

    // 跳转到商家注册页面
    @RequestMapping("/buss_register")
    public String buss_register(){
        return "buss_register";
    }

    // 跳转到发布招聘页面
    @RequestMapping("/recruit_add")
    public String recruit_add(){
        return "recruit_add";
    }

    // 跳转到課程页面
    @ResponseBody
    @RequestMapping("/course")
    public ModelAndView hello(HttpSession session){
        ModelAndView mv = new ModelAndView();
        TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
        Integer bussId = user.getBussesserId();
        mv.addObject("bussId",bussId);
        mv.setViewName("course");
        return mv;
    }

    // 跳转到課程添加页面
    @RequestMapping("/course_add")
    public String course_add(){
        return "course_add";
    }

    // 跳转到教师添加页面
    @RequestMapping("/teacher_add")
    public String teacher_add(){
        return "teacher_add";
    }

    // 跳转到登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    // 跳转到注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    // 跳转到我的信息页面
    @RequestMapping("/myInfo")
    public String my_info(){
        return "myInfo";
    }

    // 跳转到重置密码页面
    @RequestMapping("/repassword")
    public String repassword(){
        return "repassword";
    }
}

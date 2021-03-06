package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.service.IWxService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.weixin.WxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * 页面跳转Controller
 */
@Controller
@EnableAutoConfiguration
public class IndexController {

    private static Logger log = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private IWxService wxService;

    @Autowired
    private IBussesserService bussesserService;

    // 首次进来时跳到页面
    @RequestMapping("/")
    public ModelAndView index(HttpSession session){
        ModelAndView mv = new ModelAndView();
        TBussesser bussesser = (TBussesser) session.getAttribute("bussId");
        Integer bussId = Constant.BUSS_ID;
        if (bussesser != null){
            bussId = bussesser.getId();
        }
        mv.addObject("bussId",bussId);
        mv.setViewName("index");
        return mv;
    }

    // 跳转到我的页面
    @RequestMapping("/home")
    public ModelAndView home(HttpSession session){
        ModelAndView mv = new ModelAndView();
        String openId = (String) session.getAttribute("openId");
        // String openId = "oO3ww1dZFRQ3u4L41I4AfcbtNLXA";
        mv.addObject("openId",openId);
        mv.setViewName("home");
        return mv;
    }

    // 跳转到商家页面
    @RequestMapping("/buss_index")
    public ModelAndView buss(String bussId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",bussId);
        mv.setViewName("buss_index");
        return mv;
    }

    // 跳转到商家添加页面
    @RequestMapping("/buss_add")
    public ModelAndView buss_add(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("buss_add");
        return mv;
    }

    // 跳转到商家注册页面
    @RequestMapping("/buss_register")
    public String buss_register(){
        return "buss_register";
    }

    // 跳转到招聘页面
    @RequestMapping("/recruit")
    public String recruit(){
        return "recruit";
    }

    // 跳转到发布招聘页面
    @RequestMapping("/recruit_add")
    public String recruit_add(){
        return "recruit_add";
    }

    // 跳转到課程管理页面
    @RequestMapping("/course")
    public ModelAndView course(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("course");
        return mv;
    }

    // 跳转到課程添加页面
    @RequestMapping("/course_add")
    public ModelAndView course_add(String param1){
        ModelAndView mv = new ModelAndView();
        log.info("课程添加页面----------bussId="+param1);
        mv.addObject("bussId",param1);
        mv.setViewName("course_add");
        return mv;
    }

    // 跳转到教师添加页面
    @RequestMapping("/teacher_add")
    public ModelAndView teacher_add(String param1){
        ModelAndView mv = new ModelAndView();
        log.info("教师管理页面----------bussId="+param1);
        mv.addObject("bussId",param1);
        mv.setViewName("teacher_add");
        return mv;
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

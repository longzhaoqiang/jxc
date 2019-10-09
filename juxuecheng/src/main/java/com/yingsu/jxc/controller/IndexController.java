package com.yingsu.jxc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.service.IWxService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.weixin.WxController;


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

	@RequestMapping("/index")
	public ModelAndView indexs(Integer bussId){
		ModelAndView mv = new ModelAndView();
		mv.addObject("bussId",bussId);
		mv.setViewName("index");
		return mv;
	}

    // 跳转到我的页面
    @RequestMapping("/home")
    public ModelAndView home(HttpSession session){
        ModelAndView mv = new ModelAndView();
	    // String openId = (String) session.getAttribute("openId");
        String openId = "oO3ww1XNuGQW6uvtsU8SrU180340";
        mv.addObject("openId",openId);
        mv.setViewName("home");
        return mv;
    }

    // 跳转到我的页面
    @RequestMapping("/coupon")
    public ModelAndView coupon(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("coupon");
        return mv;
    }

    // 跳转设置分享页面
    @RequestMapping("/setShare")
    public ModelAndView setShare(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("set_share");
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

    // 跳转到在线报名页面
    @RequestMapping("/enroll_online")
    public ModelAndView enrollOnline(String bussId){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",bussId);
        mv.setViewName("enroll_online");
        return mv;
    }

    // 跳转联系我们页面
    @RequestMapping("/contact_us")
    public ModelAndView contact_us(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("contact_us");
        return mv;
    }

    // 跳转到商家注册页面
    @RequestMapping("/buss_register")
    public String buss_register(){
        return "buss_register";
    }

    // 跳转到招聘页面
    @RequestMapping("/recruit")
    public ModelAndView recruit(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("recruit");
        return mv;
    }

    // 跳转到发布招聘页面
    @RequestMapping("/recruit_add")
    public ModelAndView recruit_add(String param1){
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("bussId",param1);
	    mv.setViewName("recruit_add");
	    return mv;
    }

    // 跳转到課程管理页面
    @RequestMapping("/course")
    public ModelAndView course(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("course");
        return mv;
    }

    // 跳转到首页課程列表页面
    @RequestMapping("/courseCoustomer")
    public ModelAndView courseCoustomer(String param1){
        ModelAndView mv = new ModelAndView();
        mv.addObject("bussId",param1);
        mv.setViewName("course_coustomer");
        log.info("-------------跳转到首页課程列表页面");
        return mv;
    }

    // 跳转到課程添加页面
    @RequestMapping("/course_add")
    public ModelAndView course_add(String param1,String param2){
        ModelAndView mv = new ModelAndView();
        log.info("课程添加页面----------bussId="+param1);
        mv.addObject("bussId",param1);
        mv.addObject("type",param2);
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

    // 跳转到广告语页面
    @RequestMapping("/advice")
    public ModelAndView advice(String param1){
        ModelAndView mv = new ModelAndView();
        log.info("广告语页面----------bussId="+param1);
        mv.addObject("bussId",param1);
        mv.setViewName("advice");
        return mv;
    }

    // 跳转到注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    // 跳转到我的信息页面
    @RequestMapping("/myInfo")
    public ModelAndView my_info(String param1){
        ModelAndView mv = new ModelAndView();
        log.info("我的信息页面----------bussId="+param1);
        mv.addObject("bussId",param1);
        mv.setViewName("myInfo");
        return mv;
    }

    // 跳转到重置密码页面
    @RequestMapping("/repassword")
    public String repassword(){
        return "repassword";
    }
}

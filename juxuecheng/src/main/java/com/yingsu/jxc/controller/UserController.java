package com.yingsu.jxc.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.entity.TWeixinLogin;
import com.yingsu.jxc.entity.User;
import com.yingsu.jxc.jwt.JwtUtil;
import com.yingsu.jxc.service.IUserService;
import com.yingsu.jxc.service.IWxService;
import com.yingsu.jxc.service.impl.LoginThread;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.weixin.WxController;
import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @Autowired
    LoginThread loginThread;

    @Autowired
    IWxService wxService;

    /**
     * 查找微信用户详情
     */
    @RequestMapping("/getWxInfo")
    @ResponseBody
    public ResultBody getWxInfo(String openId) {
        ResultBody result = new ResultBody();
        try {
            log.info("我的页面查找微信用户详情-----------------开始");
            TWeixinLogin weixinLogin = wxService.getWeixinUser(openId);
            result.setResult(weixinLogin);
        } catch (Exception e) {
            result.setResultCode(0);
            result.setResultMsg("查找微信用户信息异常------" + e);
        }
        return result;
    }

    /**
     * 检查是否登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ResultBody checkLogin(HttpSession session) {
        ResultBody result = new ResultBody();
        String openId = (String) session.getAttribute(Constant.USER_OPENID);
        log.info("检查是否登录------------>openId=" + openId);
        TWeixinLogin weixinLogin = wxService.getWeixinUser(openId);
        if (weixinLogin == null) {
            result.setResultCode(-1);
        } else {
            result.setResult(weixinLogin);
        }
        return result;
    }
}

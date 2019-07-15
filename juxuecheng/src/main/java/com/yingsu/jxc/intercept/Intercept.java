package com.yingsu.jxc.intercept;

import com.alibaba.fastjson.JSONObject;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.RedisService;
import com.yingsu.jxc.weixin.WxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Intercept implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object){
        List<String> pathList = new ArrayList<>();
        pathList.add("/");
        pathList.add("/user/login");
        pathList.add("/user/home");
        String path = request.getServletPath();
        if (pathList.contains(path)) {
            return true;
        } else if (path.equals("/error")){
            return false;
        } else {
            if (redisTemplate == null) {//解决service为null无法注入问题
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                redisTemplate = (RedisTemplate) factory.getBean("redisTemplate");
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                JSONObject res = new JSONObject();
                String openId = (String) session.getAttribute("openId");
                log.info("------------------拦截器获取openId=" + openId);
                if (openId == null) {
                    res.put("0", "false");
                    res.put("msg", "请登录");
                    out = response.getWriter();
                    out.append(res.toString());
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

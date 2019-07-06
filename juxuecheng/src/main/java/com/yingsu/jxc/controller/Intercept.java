package com.yingsu.jxc.controller;

import com.alibaba.fastjson.JSONObject;
import com.yingsu.jxc.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class Intercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // String token = request.getParameter("token");
        /*String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7234XCJpZFwiOjg2LFwibmFtZVwiOlwiMTM4MTg1NzIwNDBcIixcInBhc3N3b3JkXCI6XCIxMTExXCJ9IiwicGFzc3dvcmQiOiJsb25nemhhb3FpYW5nIiwidXNlcl9uYW1lIjoianV4dWVjaGVuZyIsImlzcyI6Imp4YyIsImlkIjoiMTM4MTg1NzIwNDAiLCJleHAiOjE1NTk1NjM5MzAsImlhdCI6MTU1OTU2MzAzMCwianRpIjoiNzVjMWEzYmMtM2RlZS00YjQ4LTkxZTctY2EwZmExOGY0YjJiIn0.ueJVY8ULug_eXhfzksOBLlFNH2CdlhQyKFixA6t63C8";
        JwtUtil util = new JwtUtil();
        try {
            Claims claims = util.parseJWT(token);
            String object = claims.getSubject();
            JSONObject jsonObject = JSONObject.parseObject(object);
            String userName = (String) jsonObject.get("name");
            if (userName != null){
                return true;
            }
        } catch (Exception e){
            System.out.println("token错误，，请重新登录获取");
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

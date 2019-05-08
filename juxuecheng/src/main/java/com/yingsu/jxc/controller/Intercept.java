package com.yingsu.jxc.controller;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*String token = request.getParameter("Authorization");
        token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOjg2LFwibmFtZVwiOlwiMTM4MTg1NzIwNDBcIixcInBhc3N3b3JkXCI6XCIxMTExXCJ9IiwicGFzc3dvcmQiOiJsb25nemhhb3FpYW5nIiwidXNlcl9uYW1lIjoianV4dWVjaGVuZyIsImlzcyI6Imp4YyIsImlkIjoiMTM4MTg1NzIwNDAiLCJleHAiOjE1NTcxNDU3NzIsImlhdCI6MTU1NzE0MjE3MiwianRpIjoiOGE1NjU3NTktODllZi00OGM2LWJjZDYtZmNkNjBhYzRhZGYyIn0.j_NDiAivlqMtT2lRGp84AOZvfnbqS5XHSGUP2guSAOU";
        JwtUtil util = new JwtUtil();
        Claims claims = util.parseJWT(token);*/
        if (1 == 1){
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

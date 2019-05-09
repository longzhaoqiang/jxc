package com.yingsu.jxc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class TestController {

    @RequestMapping("/getCourseList")
    public String getCourseList(){
        System.out.println("获取课程列表");
        return null;
    }
}

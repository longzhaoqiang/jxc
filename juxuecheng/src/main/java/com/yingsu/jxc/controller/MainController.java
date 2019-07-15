package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TTeacher;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.ITeacherService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class MainController {

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping("/main")
    @ResponseBody
    public Map<String, Object> getIndex(HttpSession session,Integer bussId) {
        List<TTeacher> teacherList = null;
        try {
            // 获取教师列表
            ResultBody resultBody = teacherService.getTeacherList(bussId);
            teacherList = (List<TTeacher>) resultBody.getResult();

        }catch (Exception e){

        }
        Map<String, Object> map = new HashMap<>();
        map.put("teacherModel",teacherList);
        return map;
    }
}

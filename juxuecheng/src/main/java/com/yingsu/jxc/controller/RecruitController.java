package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TRecruit;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.IRecruitService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private IRecruitService recruitService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultBody add(HttpSession session, TRecruit recruit){
        ResultBody resultBody = new ResultBody();
        try {
            Integer bussId = getBussId(session);
            if (bussId == null){
                resultBody.setResultCode(0);
                return resultBody;
            }
            recruit.setBussesserId(bussId);
            resultBody = recruitService.add(recruit);
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    public Integer getBussId(HttpSession session){
        TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
        if (user != null) {
            Integer bussId = user.getBussesserId();
            return bussId;
        } else {
            return null;
        }
    }
}

package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TClasstype;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.service.IBussesserService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2018/8/23 0023.
 */
@Controller
@RequestMapping("/buss")
public class BussesserController {

    @Autowired
    private IBussesserService bussesserService;

    /**
     * 注册前查找教育类型
     * @return
     */
    @RequestMapping("/getTypeList")
    @ResponseBody
    public ResultBody getTypeList(){
        ResultBody resultBody = new ResultBody();
        try {
            // 查询数据库获取列表
            List<TClasstype> list = bussesserService.getClassTypeList();
            if (list == null){
                resultBody.setResultMsg(Constant.NO_DATA);
            } else {
                // 不为空，设置值
                resultBody.setResult(list);
            }
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 注册成商家
     * @return
     */
    @RequestMapping("/addBuss")
    @ResponseBody
    public ResultBody addBuss(HttpSession session, TBussesser bussesser){
        ResultBody resultBody = new ResultBody();
        try {
            TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
            Integer uid = user.getId();
            // 注册前先查询是否注册过
            TBussesser bussesser1 = bussesserService.getBussInfo(uid);
            if (bussesser1 != null){
                resultBody.setResultCode(Constant.EXIST_CODE);
                resultBody.setResultMsg("您已经注册过，请不要重复注册");
                return resultBody;
            }
            bussesser.setUserId(uid);
            Integer result = bussesserService.addBuss(bussesser);
            if (result != 1){
                resultBody.setResultCode(Constant.ERROR_CODE);
                resultBody.setResultMsg(Constant.ERROR_INSERT_MSG);
            }
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 检查是否注册成商家
     * @return
     */
    @RequestMapping("/checkRegister")
    @ResponseBody
    public ResultBody checkRegister(HttpSession session){
        ResultBody resultBody = new ResultBody();
        try {
            TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
            if (user != null){
                Integer bussId = user.getBussesserId();
                if (bussId != null){
                    resultBody.setResultCode(-1);
                    resultBody.setResultMsg("您已经注册过");
                } else {
                    resultBody.setResultCode(-100);
                    resultBody.setResultMsg("您尚未注册过，立即注册");
                }
            } else {
                resultBody.setResultCode(0);
            }
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }
}

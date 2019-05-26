package com.yingsu.jxc.controller;

import com.google.gson.Gson;
import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.entity.User;
import com.yingsu.jxc.jwt.JwtUtil;
import com.yingsu.jxc.service.IUserService;
import com.yingsu.jxc.util.Constant;
import com.yingsu.jxc.util.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     * 用户登录
     * @param session
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultBody userLogin(HttpServletRequest request, HttpSession session, String userName, String password){
        ResultBody resultBody = new ResultBody();
        try {
            TUser result = userService.userLogin(session, userName, password);
            if (result == null) {
                resultBody.setResultCode(0);
                resultBody.setResultMsg(Constant.ERROR_LOGIN);
                return resultBody;
            }
            session.setAttribute(Constant.USER_INFO, result);
            session.setMaxInactiveInterval(30 * 60);
            request.setAttribute(Constant.USER_INFO,result.getMobile());

            // 返回token
            User user = new User(result.getId(), userName, password);
            String subject = new Gson().toJson(user);
            JwtUtil util = new JwtUtil();
            String token = util.createJWT(Constant.JWT_ID, Constant.JWT_AUTH, subject, Constant.JWT_TTL);
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 用户注册
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResultBody userRegister(String userName,String password){
        ResultBody resultBody = new ResultBody();
        try {
            Integer result =  userService.userRegister(userName,password);
            if (result == 2){
                resultBody.setResultCode(result);
                resultBody.setResultMsg(Constant.ERROR_REGISTER);
            }
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }

        return resultBody;
    }

    /**
     * 重置密码
     * @param oldPw
     * @param newPs
     * @param reNewPw
     * @return
     */
    @RequestMapping("/resetPw_1")
    @ResponseBody
    public ResultBody resetPw(HttpSession session, String oldPw,String newPs,String reNewPw){
        ResultBody resultBody = new ResultBody();
        if (!(newPs.equals(reNewPw))){
            resultBody.setResultCode(-1);
            resultBody.setResultMsg("两次密码不一致");
            return resultBody;
        }
        try {
            // 从session中获取用户信息
            TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
            String userName = user.getUserName();
            // 先判断老密码是否正确
            TUser userLogin =  userService.userLogin(session,userName,oldPw);
            if (userLogin == null){
                resultBody.setResultCode(-10);
                resultBody.setResultMsg("旧密码不正确，请重新输入");
                return resultBody;
            }

            Integer result = userService.userUpdatePw(userName,newPs);
            if (result != 1) {
                resultBody.setResultCode(result);
                resultBody.setResultMsg("修改密码出错");
                return resultBody;
            }
        }catch (Exception e){
            resultBody.setResultCode(Constant.ERROR_CODE);
            resultBody.setResultMsg(Constant.ERROR_SYS_MSG);
        }
        return resultBody;
    }

    /**
     * 检查是否登录
     * @param session
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ResultBody checkLogin(HttpSession session){
         ResultBody result = new ResultBody();
        TUser user = (TUser) session.getAttribute(Constant.USER_INFO);
        if (user == null){
            result.setResultCode(-1);
        } else {
            result.setResult(user);
        }
        return result;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "login";
    }

}

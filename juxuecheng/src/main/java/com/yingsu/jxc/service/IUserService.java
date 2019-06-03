package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.TUser;

import javax.servlet.http.HttpSession;

public interface IUserService {

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public TUser userLogin(HttpSession session, String userName, String password) throws Exception;

    /**
     * 用户注册
     * @param userName
     * @param password
     * @return
     */
    public Integer userRegister(String userName,String password);

    /**
     * 用户修改登录密码
     * @param userName
     * @param password
     * @return
     */
    public Integer userUpdatePw(String userName,String password);

}

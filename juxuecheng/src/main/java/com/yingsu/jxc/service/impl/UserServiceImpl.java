package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TUser;
import com.yingsu.jxc.mapper.TUserMapper;
import com.yingsu.jxc.service.IUserService;
import com.yingsu.jxc.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    TUserMapper userMapper;

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public TUser userLogin(HttpSession session, String userName, String password) {
        TUser user = userMapper.selectByLogin(userName,password);
        return user;
    }

    /**
     * 用户注册
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Integer userRegister(String userName, String password) {
        TUser user1 = userMapper.selectByUserName(userName);
        Integer result = 1;
        if (user1 == null) {
            TUser user = new TUser();
            user.setUserName(userName);
            user.setPassword(password);
            user.setMobile(userName);
            result = userMapper.insertSelective(user);
        } else {
            result = 2;
        }
        return result;
    }

    /**
     * 修改登录密码
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Integer userUpdatePw(String userName, String password) {
        Integer result = userMapper.updatePwByUserName(userName,password);
        if (result != 1){
            return null;
        }
        return result;
    }
}

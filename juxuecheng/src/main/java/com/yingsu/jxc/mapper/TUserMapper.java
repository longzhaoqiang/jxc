package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TUser;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    TUser selectByUserName(String userName);

    /**
     * 用户登录时查询
     * @param userName
     * @param password
     * @return
     */
    TUser selectByLogin(@Param("userName") String userName,@Param("password") String password);

    int updatePwByUserName(String userName,String password);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}
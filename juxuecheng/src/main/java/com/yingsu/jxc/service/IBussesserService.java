package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TClasstype;

import java.util.List;

public interface IBussesserService {

    /**
     * 获取教育类型列表
     * @return
     */
    List<TClasstype> getClassTypeList();

    /**
     * 通过用户名获取商家信息
     * @param uid
     * @return
     */
    TBussesser getBussInfo(Integer uid);

    /**
     * 添加商家
     * @return
     */
    Integer addBuss(TBussesser bussesser);
}

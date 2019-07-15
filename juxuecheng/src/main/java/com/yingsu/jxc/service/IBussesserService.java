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
     * 通过id获取商家信息
     * @param id
     * @return
     */
    TBussesser getBussById(Integer id);

    /**
     * 通过openId获取商家信息
     * @param openId
     * @return
     */
    TBussesser getBussInfoOpenId(String openId);

    /**
     * 商家上传图片
     */
    Integer uploadImg(int bussId,String img);

    /**
     * 添加商家
     * @return
     */
    Integer addBuss(TBussesser bussesser);

    /**
     * 修改商家
     * @return
     */
    Integer updateBuss(TBussesser bussesser);
}

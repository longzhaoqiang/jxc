package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TClasstype;
import com.yingsu.jxc.entity.TPic;
import com.yingsu.jxc.mapper.TBussesserMapper;
import com.yingsu.jxc.mapper.TClasstypeMapper;
import com.yingsu.jxc.mapper.TPicMapper;
import com.yingsu.jxc.service.IBussesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BussesserServiceImpl implements IBussesserService {

    @Autowired
    private TBussesserMapper bussesserMapper;

    @Autowired
    private TClasstypeMapper classtypeMapper;

    @Autowired
    private TPicMapper picMapper;

    /**
     * 获取教育类型列表
     * @return
     */
    @Override
    public List<TClasstype> getClassTypeList() {
        List<TClasstype> classTypeList = classtypeMapper.findClassTypeList();
        int size = classTypeList.size();
        if (size < 1){
            return null;
        }
        return classTypeList;
    }

    /**
     * 通过用户名获取商家信息
     * @param uid
     * @return
     */
    @Override
    public TBussesser getBussInfo(Integer uid) {
        TBussesser bussesser = bussesserMapper.selectByUid(uid);
        return bussesser;
    }

    /**
     * 通过id获取商家信息
     * @param id
     * @return
     */
    @Override
    public TBussesser getBussById(Integer id){
        TBussesser bussesser = bussesserMapper.selectByPrimaryKey(id);
        return bussesser;
    }

    /**
     * 我的信息--查看商家详情
     * @param bussId
     * @return
     */
    @Override
    public Map<String, String> getBuss(Integer bussId) {
        Map<String, String> map = bussesserMapper.selectBuss(bussId);
        return map;
    }

    /**
     * 通过openId查找商家
     */
    @Override
    public TBussesser getBussInfoOpenId(String openId) {
        TBussesser bussesser = bussesserMapper.selectByOpenId(openId);
        return bussesser;
    }

    /**
     * 商家上传图片
     * @return
     */
    @Override
    public Integer uploadImg(int bussId,String img) {
        TPic pic = new TPic();
        pic.setPicUrl(img);
        pic.setBussId(bussId);
        pic.setCreateTime(new Date());
        picMapper.insertSelective(pic);
        return null;
    }

    /**
     * 添加商家
     * @param bussesser
     * @return
     */
    @Override
    public Integer addBuss(TBussesser bussesser) {
        // 插入数据库
        Integer result = bussesserMapper.insertSelective(bussesser);
        return result;
    }

    /**
     * 修改商家
     * @return
     */
    @Override
    public Integer updateBuss(TBussesser bussesser) {
        int result = bussesserMapper.updateByPrimaryKeySelective(bussesser);
        return result;
    }
}

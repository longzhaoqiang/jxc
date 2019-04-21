package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TBussesser;
import com.yingsu.jxc.entity.TClasstype;
import com.yingsu.jxc.mapper.TBussesserMapper;
import com.yingsu.jxc.mapper.TClasstypeMapper;
import com.yingsu.jxc.service.IBussesserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BussesserServiceImpl implements IBussesserService {

    @Autowired
    private TBussesserMapper bussesserMapper;

    @Autowired
    private TClasstypeMapper classtypeMapper;

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
        if (bussesser == null){
            return null;
        }
        return bussesser;
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
}

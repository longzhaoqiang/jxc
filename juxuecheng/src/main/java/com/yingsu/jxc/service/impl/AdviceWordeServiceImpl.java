package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TAdviceWord;
import com.yingsu.jxc.mapper.TAdviceWordMapper;
import com.yingsu.jxc.service.IAdivceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceWordeServiceImpl implements IAdivceService {

    @Autowired
    private TAdviceWordMapper adviceWordMapper;

    @Override
    public Integer addAdivice(TAdviceWord adviceWord) {
        Integer result = adviceWordMapper.insertSelective(adviceWord);
        return result;
    }

    @Override
    public TAdviceWord getAdvice(Integer id) {
        TAdviceWord adviceWord = adviceWordMapper.selectByPrimaryKey(id);
        return adviceWord;
    }

    @Override
    public List<TAdviceWord> getList(Integer bussId) {
        List<TAdviceWord> adviceWordList = adviceWordMapper.selectListByBussId(bussId);
        return adviceWordList;
    }

    @Override
    public Integer deleteAdvice(Integer id) {
        int result = adviceWordMapper.deleteByPrimaryKey(id);
        return result;
    }
}

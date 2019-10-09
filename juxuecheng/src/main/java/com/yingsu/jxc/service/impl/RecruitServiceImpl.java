package com.yingsu.jxc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TRecruit;
import com.yingsu.jxc.mapper.TRecruitMapper;
import com.yingsu.jxc.service.IRecruitService;

@Service
public class RecruitServiceImpl implements IRecruitService {

    @Autowired
    private TRecruitMapper recruitMapper;

    @Override
    public ResultBody add(TRecruit recruit) {
        ResultBody resultBody = new ResultBody();
        Date date = new Date();
        recruit.setCreateTime(date);
        Integer result = recruitMapper.insertSelective(recruit);
        if (result != 1){
            resultBody.setResultCode(0);
        }
        return resultBody;
    }

	@Override
	public ResultBody getList(Integer bussId) {
		ResultBody resultBody = new ResultBody();
		List<TRecruit> recruits = recruitMapper.selectList(bussId);
		resultBody.setResult(recruits);
		return resultBody;
	}
}

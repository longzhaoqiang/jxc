package com.yingsu.jxc.service.impl;

import com.yingsu.jxc.entity.TThread;
import com.yingsu.jxc.mapper.TThreadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class LoginThread implements Runnable{

    @Autowired
    private TThreadMapper threadMapper;

    String msg;

    @Override
    public void run() {
        System.out.println("线程开始执行-------------");
        sendMsg("有线程在执行了");
        System.out.println("线程执行结束-------------");
    }

    public Integer sendMsg(String msg){
        TThread tThread = new TThread();
        tThread.setMessage(msg);
        tThread.setCreatTime(new Date());
        threadMapper.insertSelective(tThread);
        return 0;
    }
}

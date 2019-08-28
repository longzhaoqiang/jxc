package com.yingsu.jxc.controller;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TAdviceWord;
import com.yingsu.jxc.service.IAdivceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/advice")
public class AdviceWordController {

    private static Logger log = LoggerFactory.getLogger(AdviceWordController.class);

    @Autowired
    private IAdivceService adivceService;


    @RequestMapping("/add")
    @ResponseBody
    public ResultBody addAdvice(TAdviceWord adviceWord){
        ResultBody resultBody = new ResultBody();
        try {
            int result = adivceService.addAdivice(adviceWord);
            resultBody.setResultCode(result);
        }catch (Exception e){
            log.error("插入广告语异常---->"+e);
        }
        return resultBody;
    }

    @RequestMapping("/get")
    public ResultBody getAdvice(Integer id){
        ResultBody resultBody = new ResultBody();
        try {
            TAdviceWord adviceWord = adivceService.getAdvice(id);
            if (adviceWord == null){
                resultBody.setResultCode(0);
                resultBody.setResultMsg("暂无数据");
                return resultBody;
            }
            resultBody.setResult(adviceWord);
        }catch (Exception e){
            log.error("查询广告语异常---->"+e);
        }
        return null;
    }

    @RequestMapping("/getList")
    @ResponseBody
    public ResultBody getAdviceList(Integer bussId){
        ResultBody resultBody = new ResultBody();
        try {
            List<TAdviceWord> adviceWordList = adivceService.getList(bussId);
            if (adviceWordList.size() == 0){
                resultBody.setResultCode(0);
                resultBody.setResultMsg("暂无数据");
                return resultBody;
            }
            resultBody.setResult(adviceWordList);
        }catch (Exception e){
            log.error("查询广告语异常---->"+e);
        }
        return resultBody;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultBody deleteAdvice(Integer id){
        ResultBody resultBody = new ResultBody();
        try {
            int result = adivceService.deleteAdvice(id);
            if (result != 1){
                resultBody.setResultCode(0);
            }
        }catch (Exception e){
            log.error("删除广告语异常---->"+e);
        }
        return resultBody;
    }
}

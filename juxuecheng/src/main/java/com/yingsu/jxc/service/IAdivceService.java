package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.TAdviceWord;
import io.swagger.models.auth.In;

import java.util.List;

public interface IAdivceService {

    Integer addAdivice(TAdviceWord adviceWord);

    TAdviceWord getAdvice(Integer id);

    List<TAdviceWord> getList(Integer bussId);

    Integer deleteAdvice(Integer id);
}

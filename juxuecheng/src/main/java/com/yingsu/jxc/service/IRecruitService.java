package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TRecruit;

public interface IRecruitService {

    /**
     * 添加招聘
     * @param recruit
     * @return
     */
    public ResultBody add(TRecruit recruit);
}

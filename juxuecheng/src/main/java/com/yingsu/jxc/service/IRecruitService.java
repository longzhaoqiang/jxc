package com.yingsu.jxc.service;

import com.yingsu.jxc.entity.ResultBody;
import com.yingsu.jxc.entity.TRecruit;

public interface IRecruitService {

    /**
     * 添加招聘
     * @param recruit
     * @return
     */
    ResultBody add(TRecruit recruit);

	/**
	 * 获取招聘列表
	 * @param bussId
	 * @return
	 */
	ResultBody getList(Integer bussId);
}

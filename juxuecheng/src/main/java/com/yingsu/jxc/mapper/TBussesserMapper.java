package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TBussesser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TBussesserMapper {

    /**
     * 根据id查询商家信息
     * @param id
     * @return
     */
    TBussesser selectByPrimaryKey(Integer id);

    /**
     *  查找商家数量
     * @param bussId
     * @param bussType
     * @return
     */
    Integer findBussCount(Integer bussId,String bussType);

    /**
     * 根据uid查询商家信息
     * @param uid
     * @return
     */
    TBussesser selectByUid(Integer uid);

    /**
     * 我的信息--查看商家详情
     * @param id
     * @return
     */
    Map<String, String> selectBuss(Integer id);

    /**
     * 通过openId查找商家信息
     * @param openId
     * @return
     */
    TBussesser selectByOpenId(String openId);

    /**
     * 添加商家
     * @param record
     * @return
     */
    int insertSelective(TBussesser record);

    /**
     * 修改商家
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TBussesser record);

}
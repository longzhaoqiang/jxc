package com.yingsu.jxc.mapper;

import com.yingsu.jxc.entity.TBussesser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface TBussesserMapper {

    /**
     * 根据id查询商家信息
     * @param id
     * @return
     */
    TBussesser selectByPrimaryKey(Integer id);

    /**
     * 根据uid查询商家信息
     * @param uid
     * @return
     */
    TBussesser selectByUid(Integer uid);

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
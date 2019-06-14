package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWb;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:03:36.
 */
@Repository
public interface BaseWbMapper extends DeleteLogicMapper<BaseWb>, MyMapperAndIds<BaseWb> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWb> selectPage(Map map);

    /**
     * 根据地码和删除标记获取工作台信息
     *
     * @param berCode
     * @param deleteFlag
     * @return
     */
    BaseWb selectByBerCodeAndDeleteFlag(@Param("berCode") String berCode, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据工作台编号获取未删除的有效点位信息
     *
     * @param wbCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseWb selectByWbCodeAndValidFlagAndDeleteFlag(@Param("wbCode") String wbCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据地图编号更新库区代码
     *
     * @param mapCode
     * @param areaCode
     * @return
     */
    int updateAreaCodeByMapCode(@Param("mapCode") String mapCode, @Param("areaCode") String areaCode);

    /**
     * 获取空闲点位
     *
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    List<BaseWb> selectFreeWbInfoByValidFlagAndDeleteFlag(@Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);
}
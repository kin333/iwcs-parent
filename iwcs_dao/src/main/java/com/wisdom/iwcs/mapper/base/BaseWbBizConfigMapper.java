package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-20 18:36:31.
 */
@Repository
public interface BaseWbBizConfigMapper extends MyMapperAndIds<BaseWbBizConfig> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWbBizConfig> selectPage(Map map);

    /**
     * 根据工作台编码、业务类型获取工作台配置信息
     *
     * @param wbCode
     * @param bizType
     * @return
     */
    BaseWbBizConfig selectByWbCodeAndBizType(@Param("wbCode") String wbCode, @Param("bizType") String bizType);
}
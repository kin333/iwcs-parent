package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OrderActionConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:46:36.
 */
@Repository
public interface OrderActionConfigMapper extends MyMapperAndIds<OrderActionConfig> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OrderActionConfig> selectPage(Map map);

    /**
     * 根据配置号获取配置信息
     *
     * @param configCode
     * @return
     */
    OrderActionConfig selectByConfigCode(String configCode);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int updateList(List<OrderActionConfig> list);
}
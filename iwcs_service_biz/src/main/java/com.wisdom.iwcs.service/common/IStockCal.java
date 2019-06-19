package com.wisdom.iwcs.service.common;

import com.wisdom.iwcs.commonDto.fliterCondition.stockCondition.OutstockCalConditon;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDTO;

import java.util.List;

/**
 * 库存计算
 *
 * @author ted
 * @create 2019-03-04 下午2:58
 **/
public interface IStockCal {
    /**
     * 条件计算出库
     *
     * @return
     */
    List<OutstockCalPodResultDTO> calStockByStockCondition(OutstockCalConditon outstockCalConditon);
}

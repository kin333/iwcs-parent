package com.wisdom.iwcs.mapper.stock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockConditionDto;
import com.wisdom.iwcs.domain.stock.dto.StockDetialDto;
import com.wisdom.iwcs.domain.stock.dto.StockQueryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:18:24.
 */
@Repository
public interface StockMapper extends DeleteLogicMapper<Stock>, MyMapperAndIds<Stock> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<Stock> selectPage(Map map);

    /**
     * 根据货架编号判断该货架是否有库内数量大于0的库存数据
     *
     * @param podCode
     * @return
     */
    int selectCountExistValidTotalQtyStockByPodCode(String podCode);

    /**
     * 根据库存已知、未知属性获取唯一库存
     *
     * @param stockQueryDTO
     * @return
     */
    Stock selectStockByStockCharacter(StockQueryDTO stockQueryDTO);

    int selectCountByBincodeAndDeleteFlag(@Param("binCode") String binCode, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据货架号获取存在有效库存且未删除的库存信息
     *
     * @param podCode
     * @param deleteFlag
     * @return
     */
    List<Stock> selectExistAvailableQtyStockByPodCodeAndDeleteFlag(@Param("podCode") String podCode, @Param("deleteFlag") Integer deleteFlag);

    List<Stock> selectAvailableStockByStockCharacter(OutstockCalPodParamDTO outstockCalPodParamDTO);

    List<Stock> selectAvailableStockBySns(List<String> sns);

    /**
     * 根据盘点条件 获取存库表中相应的库存信息
     *
     * @param stock
     * @return
     */
    List<Stock> invTaskCondQuery(@Param("stock") Stock stock, @Param("days") Long days);

    /**
     * 根据库存编号获取未删除的库存信息
     *
     * @param stkCode
     * @param deleteFlag
     * @return
     */
    Stock selectStockByStkCodeAndDeleteFlag(@Param("stkCode") String stkCode, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据物料编号查询是否存在有效库存
     *
     * @param matCode
     * @return
     */
    Stock selectStockByMatCode(String matCode);


    List<Stock> selectStock(StockConditionDto stockConditionDto);

    StockDetialDto selectStockDeatilInfo();

}

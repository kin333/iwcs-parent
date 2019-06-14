package com.wisdom.iwcs.mapper.stock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.stock.StockSn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:19:48.
 */
@Repository
public interface StockSnMapper extends DeleteLogicMapper<StockSn>, MyMapperAndIds<StockSn> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<StockSn> selectPage(Map map);

    int deleteByStkCodeAndSns(@Param("stkCode") String stkCode, @Param("sns") List<String> sns);

    List<StockSn> selectByStkCodeList(List<String> stkCodeList);

    List<StockSn> selectByStkCode(String stkCode);

    /**
     * 根据库存编号、sn信息获取有效未删除的库存SN信息
     *
     * @param stkCode
     * @param sn
     * @param validFlag
     * @param deletedFlag
     * @return
     */
    StockSn selectByStkCodeAndSnAndValidFlagAndDeleteFlag(@Param("stkCode") String stkCode, @Param("sn") String sn, @Param("validFlag") Integer validFlag, @Param("deletedFlag") Integer deletedFlag);

    List<StockSn> selectStockSn(String stkCode);

}

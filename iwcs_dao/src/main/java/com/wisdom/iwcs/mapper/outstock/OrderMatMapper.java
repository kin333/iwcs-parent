package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OrderMat;
import com.wisdom.iwcs.domain.outstock.dto.OutStockOrderDto;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:00:05.
 */
@Repository
public interface OrderMatMapper extends MyMapperAndIds<OrderMat> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OrderMat> selectPage(Map map);

    /**
     * 根据订单号、订单行号获取订单信息
     *
     * @param bizOrderCode
     * @param srcOrderItem
     * @return
     */
    OrderMat selectByBizOrderCodeAndSrcOrderItem(@Param("bizOrderCode") String bizOrderCode, @Param("srcOrderItem") String srcOrderItem);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int updateList(List<OrderMat> list);

    /**
     * 根据出库单号count已经开始出库的订单
     *
     * @param bizOrderCode
     * @return
     */
    int selectCountBeginOutOrderByBizOrderCode(String bizOrderCode);

    /**
     * 根据订单号获取去重后的唯一材料编号
     *
     * @param bizOrderCodes
     * @return
     */
    List<String> selectDistinctOrderMatGenCodeByBizOrderCodes(List<String> bizOrderCodes);

    /**
     * 根据订单号批量删除
     *
     * @param bizOrderCodes
     * @return
     */
    int deleteByBizOrderCodes(List<String> bizOrderCodes);

    /**
     * 根据单号、单行号或者整单计算获取出库的库存特征+数量
     *
     * @param bizWholeOrderNos
     * @param bizOrderCodes
     * @param areaCode
     * @return
     */
    List<OutstockCalPodParamDTO> selectNotPreSnReadyOutstockCharacterByBizWholeOrderNoOrBizOrderCodesAndAreaCode(@Param("bizWholeOrderNos") List<String> bizWholeOrderNos, @Param("bizOrderCodes") List<String> bizOrderCodes, @Param("areaCode") String areaCode);

    /**
     * 根据单号+行号或者整单计算出库SN
     *
     * @param bizWholeOrderNos
     * @param bizOrderCodes
     * @param areaCode
     * @return
     */
    List<String> selectOutSnByBizWholeOrderNoOrBizOrderCodesAndAreaCode(@Param("bizWholeOrderNos") List<String> bizWholeOrderNos, @Param("bizOrderCodes") List<String> bizOrderCodes, @Param("areaCode") String areaCode);

    List<OutStockOrderDto> selectOutStockOrder(OutStockOrderDto outStockOrderDto);

}

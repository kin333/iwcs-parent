package com.wisdom.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wisdom.controller.mapstruct.outstock.OutstockBizOrderMapStruct;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.*;
import com.wisdom.iwcs.domain.outstock.dto.*;
import com.wisdom.iwcs.mapper.outstock.*;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.outstock.IOutstockBizOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.outStockUtils.ConfigTypeConstants.TRUCK_ORDER;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockOrderConstants.OrderStatusConstants.ORDER_CREATED;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.NOT_PRE_SN;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.PRE_SN;
import static com.wisdom.iwcs.common.utils.unitUtils.OutUnitEnum.PACKAGE_UNIT;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutstockBizOrderService implements IOutstockBizOrderService {
    private final Logger logger = LoggerFactory.getLogger(OutstockBizOrderService.class);

    private final OutstockBizOrderMapper outstockBizOrderMapper;

    private final OutstockBizOrderMapStruct outstockBizOrderMapStruct;

    @Autowired
    private OrderMatMapper orderMatMapper;
    @Autowired
    private MatConfigRelationMapper matConfigRelationMapper;
    @Autowired
    private OrderActionConfigMapper orderActionConfigMapper;
    @Autowired
    private OutstockOrderSnMapper outstockOrderSnMapper;

    @Autowired
    public OutstockBizOrderService(OutstockBizOrderMapStruct outstockBizOrderMapStruct, OutstockBizOrderMapper outstockBizOrderMapper) {
        this.outstockBizOrderMapStruct = outstockBizOrderMapStruct;
        this.outstockBizOrderMapper = outstockBizOrderMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link OutstockBizOrderDTO }
     * @return int
     */
    @Override
    public int insert(OutstockBizOrderDTO record) {
        OutstockBizOrder outstockBizOrder = outstockBizOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        outstockBizOrder.setCreatedTime(new Date());


        int num = outstockBizOrderMapper.insert(outstockBizOrder);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<OutstockBizOrderDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<OutstockBizOrderDTO> records) {
        List<OutstockBizOrder> recordList = outstockBizOrderMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = outstockBizOrderMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link OutstockBizOrderDTO }
     */
    @Override
    public OutstockBizOrderDTO selectByPrimaryKey(Integer id) {

        OutstockBizOrder outstockBizOrder = outstockBizOrderMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(outstockBizOrder, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return outstockBizOrderMapStruct.toDto(outstockBizOrder);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link OutstockBizOrderDTO }
     * @return {@link List<OutstockBizOrderDTO> }
     */
    @Override
    public List<OutstockBizOrderDTO> selectSelective(OutstockBizOrderDTO record) {
        OutstockBizOrder outstockBizOrder = outstockBizOrderMapStruct.toEntity(record);

        List<OutstockBizOrder> outstockBizOrderList = outstockBizOrderMapper.select(outstockBizOrder);
        return outstockBizOrderMapStruct.toDto(outstockBizOrderList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link OutstockBizOrderDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(OutstockBizOrderDTO record) {
        OutstockBizOrder outstockBizOrder = outstockBizOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = outstockBizOrderMapper.updateByPrimaryKey(outstockBizOrder);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link OutstockBizOrderDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(OutstockBizOrderDTO record) {
        OutstockBizOrder outstockBizOrder = outstockBizOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = outstockBizOrderMapper.updateByPrimaryKeySelective(outstockBizOrder);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = outstockBizOrderMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return outstockBizOrderMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<OutstockBizOrderDTO> }
     */
    @Override
    public GridReturnData<OutstockBizOrderDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<OutstockBizOrderDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<OutstockBizOrder> list = outstockBizOrderMapper.selectPage(map);

        PageInfo<OutstockBizOrder> pageInfo = new PageInfo<>(list);
        PageInfo<OutstockBizOrderDTO> pageInfoFinal = new PageInfo<>(outstockBizOrderMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 创建、更新出库订单信息
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result syncOutstockOrderInfo(InitOutStockOrderRequestDTO requestDTO) {
        List<InitOutstockOrderDataDTO> insertOutStockOrderList = new ArrayList<>();
        List<InitOutstockOrderDataDTO> updateOutStockOrderList = new ArrayList<>();

        requestDTO.getData().stream().forEach(data -> {
            String requestBizOrderCode = data.getBizOrderCode();
            OutstockBizOrder existsBizOrder = outstockBizOrderMapper.selectByBizOrderCode(requestBizOrderCode);
            if (existsBizOrder == null) {
                insertOutStockOrderList.add(data);
            } else {
                updateOutStockOrderList.add(data);
            }
        });

        if (insertOutStockOrderList.size() != 0) {
            insertOutStockOrder(insertOutStockOrderList);
        }
        if (updateOutStockOrderList.size() != 0) {
            updateOutStockOrder(updateOutStockOrderList);
        }


        return new Result();
    }

    /**
     * 组装插入出库单数据，批量插入
     *
     * @param insertList
     */
    private void insertOutStockOrder(List<InitOutstockOrderDataDTO> insertList) {

        List<OutstockBizOrder> bizOrderInsertList = new ArrayList<>();
        List<OrderMat> orderMatInsertList = new ArrayList<>();
        List<OutstockOrderSn> orderSnInsertList = new ArrayList<>();
        List<InitOutstockOrderDataDTO> maintainConfigList = new ArrayList<>();
        insertList.stream().forEach(data -> {

            OutstockBizOrder outstockBizOrder = new OutstockBizOrder();
            outstockBizOrder.setBizOrderCode(data.getBizOrderCode());
            outstockBizOrder.setCreatedTime(new Date());
            outstockBizOrder.setOrderType(data.getOrderType());
            if (!bizOrderInsertList.contains(outstockBizOrder)) {
                bizOrderInsertList.add(outstockBizOrder);
            }
            String orderMatGenCode = UUID.randomUUID().toString().replace("-", "");
            OrderMat orderMat = new OrderMat();
            orderMat.setOrderMatGenCode(orderMatGenCode);
            orderMat.setBizOrderCode(data.getBizOrderCode());
            orderMat.setSrcOrderItem(data.getSrcOrderItem());
            orderMat.setMatCode(data.getMatCode());
            BigDecimal outQty = returnOrderMatOutQty(data.getOutQty(), data.getSns());
            orderMat.setOutQty(outQty);
            orderMat.setBatchNum(data.getBatchNum());
            orderMat.setCargoOwner(data.getCargoOwner());
            orderMat.setAreaCode(data.getAreaCode());
            orderMat.setOutUnit(PACKAGE_UNIT.getCode());
            orderMat.setCreatedTime(new Date());
            orderMat.setOrderMatStatus(ORDER_CREATED);
            orderMat.setOutStorageNum(new BigDecimal(0));
            orderMat.setStkCharacter1(data.getStkCharacter1());
            orderMat.setStkCharacter2(data.getStkCharacter2());
            orderMat.setStkCharacter3(data.getStkCharacter3());
            orderMat.setStkCharacter4(data.getStkCharacter4());
            orderMat.setStkCharacter5(data.getStkCharacter5());
            orderMat.setBizWholeOrderNo(data.getBizOrderCode() + data.getSrcOrderItem());
            orderMat.setContainerCode(data.getContainerCode());
            orderMat.setSpecCode(data.getSpecCode());
            if (data.getSns() != null && data.getSns().size() != 0) {
                orderMat.setPreSnFlag(PRE_SN);
                data.getSns().stream().forEach(sn -> {
                    OutstockOrderSn outstockOrderSn = new OutstockOrderSn();
                    outstockOrderSn.setOrderMatGenCode(orderMatGenCode);
                    outstockOrderSn.setSn(sn);
                    outstockOrderSn.setValidFlag(ValidFlagEnum.VALID.getStatus());
                    outstockOrderSn.setDeleteFlag(NOT_DELETED.getStatus());
                    outstockOrderSn.setCreatedTime(new Date());
                    orderSnInsertList.add(outstockOrderSn);
                });
            } else {
                orderMat.setPreSnFlag(NOT_PRE_SN);
            }
            orderMatInsertList.add(orderMat);

            if (!Strings.isNullOrEmpty(data.getConfigCode())) {
                data.setOrderMatGenCode(orderMatGenCode);
                maintainConfigList.add(data);
            }
        });

        if (bizOrderInsertList.size() != 0) {
            outstockBizOrderMapper.insertList(bizOrderInsertList);
        }

        if (orderMatInsertList.size() != 0) {
            orderMatMapper.insertList(orderMatInsertList);
        }

        if (orderSnInsertList.size() != 0) {
            outstockOrderSnMapper.insertList(orderSnInsertList);
        }
        if (maintainConfigList.size() != 0) {
            maintainMatConfigRelation(maintainConfigList);
        }

    }

    /**
     * 维护配置关系
     *
     * @param configDatas
     */
    private void maintainMatConfigRelation(List<InitOutstockOrderDataDTO> configDatas) {
        List<OrderActionConfig> configInsertList = new ArrayList<>();
        List<OrderActionConfig> configUpdateList = new ArrayList<>();
        List<MatConfigRelation> insertRelationList = new ArrayList<>();

        configDatas.stream().forEach(configData -> {
            boolean needNewOrderConfig = false;
            boolean needNewConfigRelation = false;
            String requestConfigCode = configData.getConfigCode();
            String requestMatGenCode = configData.getOrderMatGenCode();
            OrderActionConfig orderActionConfig = orderActionConfigMapper.selectByConfigCode(requestConfigCode);
            if (orderActionConfig == null) {
                orderActionConfig = new OrderActionConfig();
                needNewOrderConfig = true;
            }
            orderActionConfig.setConfigCode(requestConfigCode);
            orderActionConfig.setConfigType(TRUCK_ORDER);
            orderActionConfig.setConfigProp1(configData.getConfigProp1());
            orderActionConfig.setConfigProp2(configData.getConfigProp2());
            orderActionConfig.setConfigProp3(configData.getConfigProp3());
            orderActionConfig.setConfigProp4(configData.getConfigProp4());
            orderActionConfig.setConfigProp5(configData.getConfigProp5());

            if (needNewOrderConfig && !configInsertList.contains(orderActionConfig)) {
                orderActionConfig.setCreatedTime(new Date());
                configInsertList.add(orderActionConfig);
                needNewConfigRelation = true;
            } else {
                orderActionConfig.setLastModifiedTime(new Date());
                configUpdateList.add(orderActionConfig);
                MatConfigRelation matConfigRelation = matConfigRelationMapper.selectByOrderMatGenCode(requestMatGenCode);
                if (matConfigRelation == null) {
                    needNewConfigRelation = true;
                }
            }

            if (needNewConfigRelation) {
                MatConfigRelation matConfigRelation = new MatConfigRelation();
                matConfigRelation.setConfigCode(requestConfigCode);
                matConfigRelation.setOrderMatGenCode(requestMatGenCode);
                matConfigRelation.setCreatedTime(new Date());
                insertRelationList.add(matConfigRelation);
            }

        });

        if (configInsertList.size() != 0) {
            orderActionConfigMapper.insertList(configInsertList);
        }
        if (configUpdateList.size() != 0) {
            orderActionConfigMapper.updateList(configUpdateList);
        }
        if (insertRelationList.size() != 0) {
            matConfigRelationMapper.insertList(insertRelationList);
        }

    }


    /**
     * 组装更新出库单数据，批量更新
     *
     * @param updateList
     */
    private void updateOutStockOrder(List<InitOutstockOrderDataDTO> updateList) {
        List<InitOutstockOrderDataDTO> maintainConfigDataList = new ArrayList<>();
        List<OutstockOrderSn> orderSnInsertList = new ArrayList<>();
        List<OrderMat> updateOrderMatList = new ArrayList<>();
        updateList.stream().forEach(data -> {
            OrderMat orderMat = orderMatMapper.selectByBizOrderCodeAndSrcOrderItem(data.getBizOrderCode(), data.getSrcOrderItem());
            checkUpdateOrderMatParam(orderMat, data);
            data.setOrderMatGenCode(orderMat.getOrderMatGenCode());
            orderMat.setMatCode(data.getMatCode());
            BigDecimal outQty = returnOrderMatOutQty(data.getOutQty(), data.getSns());
            orderMat.setOutQty(outQty);
            orderMat.setBatchNum(data.getBatchNum());
            orderMat.setCargoOwner(data.getCargoOwner());
            orderMat.setAreaCode(data.getAreaCode());
            orderMat.setSpecCode(data.getSpecCode());
            orderMat.setContainerCode(data.getContainerCode());
            orderMat.setStkCharacter1(data.getStkCharacter1());
            orderMat.setStkCharacter2(data.getStkCharacter2());
            orderMat.setStkCharacter3(data.getStkCharacter3());
            orderMat.setStkCharacter4(data.getStkCharacter4());
            orderMat.setStkCharacter5(data.getStkCharacter5());
            if (isSnOrderMat(data, orderMat)) {
                data.getSns().stream().forEach(sn -> {
                    OutstockOrderSn outstockOrderSn = new OutstockOrderSn();
                    outstockOrderSn.setOrderMatGenCode(orderMat.getOrderMatGenCode());
                    outstockOrderSn.setSn(sn);
                    outstockOrderSn.setValidFlag(ValidFlagEnum.VALID.getStatus());
                    outstockOrderSn.setDeleteFlag(NOT_DELETED.getStatus());
                    outstockOrderSn.setCreatedTime(new Date());
                    orderSnInsertList.add(outstockOrderSn);
                });
            }
            updateOrderMatList.add(orderMat);
            if (!Strings.isNullOrEmpty(data.getConfigCode())) {
                maintainConfigDataList.add(data);
            }
        });
        orderMatMapper.updateList(updateOrderMatList);
        if (orderSnInsertList.size() != 0) {
            List<String> orderMatGenCodes = updateList.stream().map(InitOutstockOrderDataDTO::getOrderMatGenCode).distinct().collect(Collectors.toList());
            outstockOrderSnMapper.deleteByOrderMatGenCodes(orderMatGenCodes);
            outstockOrderSnMapper.insertList(orderSnInsertList);
        }
        if (maintainConfigDataList.size() != 0) {
            maintainMatConfigRelation(maintainConfigDataList);
        }

    }

    /**
     * 判断是否为指定SN 的出库单
     *
     * @param data
     * @param orderMat
     * @return
     */
    private boolean isSnOrderMat(InitOutstockOrderDataDTO data, OrderMat orderMat) {
        return orderMat.getPreSnFlag().equals(PRE_SN) && data.getSns() != null && data.getSns().size() != 0;
    }

    /**
     * 判断更新出库单的条件
     *
     * @param existsOrderMat
     * @param requestData
     */
    private void checkUpdateOrderMatParam(OrderMat existsOrderMat, InitOutstockOrderDataDTO requestData) {
        BigDecimal outStorageNum = existsOrderMat.getOutStorageNum();
        boolean checkExistsOutStorageNum = outStorageNum.compareTo(BigDecimal.ZERO) == 0;
        //如果在出库流程，更新的数量无法补充更新出库货架
        Preconditions.checkArgument(checkExistsOutStorageNum, "出库单号" + existsOrderMat.getBizOrderCode() + "，订单行号" + existsOrderMat.getSrcOrderItem() + "已进入出库流程，更新失败");

    }

    /**
     * 删除出库单信息
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result deleteOutStockOrderInfo(DeleteOutStockOrderRequestDTO requestDTO) {
        List<String> requestBizOrderCodes = requestDTO.getData().stream().map(DeleteOutStockOrderDataDTO::getBizOrderCode).distinct().collect(Collectors.toList());
        checkDeleteOutStockOrderInfoParam(requestBizOrderCodes);
        List<String> deleteOrderMatGenCodes = orderMatMapper.selectDistinctOrderMatGenCodeByBizOrderCodes(requestBizOrderCodes);
        outstockBizOrderMapper.deleteByBizOrderCodes(requestBizOrderCodes);
        orderMatMapper.deleteByBizOrderCodes(requestBizOrderCodes);
        outstockOrderSnMapper.deleteByOrderMatGenCodes(deleteOrderMatGenCodes);
        matConfigRelationMapper.deleteByOrderMatGenCodes(deleteOrderMatGenCodes);
        return new Result();
    }

    /**
     * 校验删除出库单
     *
     * @param bizOrderCodes
     */
    private void checkDeleteOutStockOrderInfoParam(List<String> bizOrderCodes) {
        bizOrderCodes.stream().forEach(bizOrderCode -> {
            int count = orderMatMapper.selectCountBeginOutOrderByBizOrderCode(bizOrderCode);
            Preconditions.checkArgument(count == 0, "出库单号" + bizOrderCode + "已开始出库，不允许删除该订单");
        });
    }

    /**
     * 根据数量和sn返回出库数量
     *
     * @param outQty
     * @param sn
     * @return
     */
    private BigDecimal returnOrderMatOutQty(BigDecimal outQty, List<String> sn) {
        BigDecimal returnOutQty = new BigDecimal(0);
        if (sn != null && sn.size() != 0) {
            returnOutQty = new BigDecimal(sn.size());
        } else {
            returnOutQty = outQty;
        }
        return returnOutQty;
    }
}

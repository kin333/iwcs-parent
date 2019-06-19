package com.wisdom.iwcs.service.outstock.outstockImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.ListUtils;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.outstock.MatConfigRelation;
import com.wisdom.iwcs.domain.outstock.OrderMat;
import com.wisdom.iwcs.domain.outstock.OutstockRecord;
import com.wisdom.iwcs.domain.outstock.OutstockRecordDetail;
import com.wisdom.iwcs.domain.outstock.dto.*;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockQueryDTO;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.outstock.MatConfigRelationMapper;
import com.wisdom.iwcs.mapper.outstock.OrderMatMapper;
import com.wisdom.iwcs.mapper.outstock.OutstockRecordDetailMapper;
import com.wisdom.iwcs.mapper.outstock.OutstockRecordMapper;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.stock.StockSnMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskDetailOutstockProcessMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IReturnPodService;
import com.wisdom.iwcs.service.outstock.IOutBoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockCallTypeConstants.NOT_ORDER_CALL;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockCallTypeConstants.ORDER_CALL;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockOrderConstants.OrderStatusConstants.ORDER_END_OUT;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockOrderConstants.OrderStatusConstants.ORDER_START_OUT;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockOrderConstants.OrderTypeConstants.VIRTUAL_OUT_ORDER;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockTypeConstants.*;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.NOT_PRE_SN;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.PRE_SN;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.OUTSTOCK_TASK;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvAfterArrivalActionConstants.AGV_LEAVE;
import static com.wisdom.iwcs.common.utils.unitUtils.OutUnitEnum.PACKAGE_UNIT;

/**
 * 出库确认
 *
 * @author cecilia.yang
 * @version 2019/02/25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutBoundService implements IOutBoundService {
    private final Logger logger = LoggerFactory.getLogger(OutBoundService.class);
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private OrderMatMapper orderMatMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockSnMapper stockSnMapper;
    @Autowired
    private OutstockRecordMapper outstockRecordMapper;
    @Autowired
    private MatConfigRelationMapper matConfigRelationMapper;
    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private OutstockRecordDetailMapper outstockRecordDetailMapper;
    @Autowired
    private com.wisdom.iwcs.service.outstock.IOutstockBizOrderService IOutstockBizOrderService;
    @Autowired
    private IReturnPodService IReturnPodService;
    @Autowired
    private AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper;
    @Autowired
    private ApplicationProperties applicationProperties;


    /**
     * 出库确认
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result outBoundConfirm(OutBoundRequestDTO requestDTO) {
        String requestAreaCode = requestDTO.getAreaCode();
        String requestOutCallType = requestDTO.getOutCallType();
        checkOutBoundConfirmParam(requestDTO);
        String randomPodCode = requestDTO.getData().get(0).getBincode();
        String requestWbCode = ICommonService.returnWbCodeByWbCodeOrByTaskPodCodeAndTaskType(requestDTO.getWbCode(), randomPodCode, OUTSTOCK_TASK.getType());
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(requestWbCode);
        caseForceKeepOutstockProcessThenCheckOutBoundData(requestDTO, wbAgvTask.getTaskNo());
        if (requestOutCallType.equals(NOT_ORDER_CALL)) {
            outBoundNewVirtualOrder(wbAgvTask.getBizOrderCode(), requestDTO);
        }
        List<UpdateOustockProcessDTO> updateOustockProcessDTOList = new ArrayList<>();
        requestDTO.getData().stream().forEach(data -> {
            String orderMatGenCode = "";
            BigDecimal outStorageNum = returnOutBoundNum(data.getOutQty(), data.getSn());
            OrderMat orderMat = new OrderMat();
            if (requestOutCallType.equals(ORDER_CALL)) {
                orderMat = updateOrderMatOutInfo(data.getBizOrderCode(), data.getSrcOrderItem(), outStorageNum);
            } else {
                orderMat = updateOrderMatOutInfo(wbAgvTask.getBizOrderCode(), data.getMatCode(), outStorageNum);
            }
            orderMatGenCode = orderMat.getOrderMatGenCode();
            Stock stock = getStockByOutBoundData(requestAreaCode, data);
            BigDecimal currentTotalQty = stock.getTotalQty();
            String stkCode = stock.getStkCode();

            //部分出时更新库存
            BigDecimal resultAvailableQty = stock.getAvailableQty().subtract(outStorageNum);
            BigDecimal resultTotalQty = resultAvailableQty.add(stock.getLockQty());
            stock.setAvailableQty(resultAvailableQty);
            stock.setTotalQty(resultTotalQty);
            stockMapper.updateByPrimaryKeySelective(stock);

            UpdateOustockProcessDTO updateOustockProcessDTO = new UpdateOustockProcessDTO();
            if (data.getSn() != null && data.getSn().size() != 0) {
                stockSnMapper.deleteByStkCodeAndSns(stkCode, data.getSn());
                String outSn = ListUtils.convertListToString(data.getSn());
                updateOustockProcessDTO.setActualOutSns(outSn);
            }
            //出库记录
            recordOutStockRecode(data, orderMatGenCode, requestAreaCode, stock, requestDTO.getUserId(), wbAgvTask.getSrcReqCode());
            updateOustockProcessDTO.setTaskNo(wbAgvTask.getTaskNo());
            updateOustockProcessDTO.setBinCode(data.getBincode());
            updateOustockProcessDTO.setStkCode(stkCode);
            updateOustockProcessDTO.setOutStorageNum(outStorageNum);
            updateOustockProcessDTOList.add(updateOustockProcessDTO);
        });

        List<String> requestBinCodes = requestDTO.getData().stream().map(OutBoundDataDTO::getBincode).distinct().collect(Collectors.toList());
        updateOustockProcess(updateOustockProcessDTOList);
        if (isAgvLeaveOutBound(requestDTO)) {
            String returnBincode = ICommonService.checkReturnBincodesIfHaveDifferentPodAndReturnOneRandomBincode(requestBinCodes);
            outboundReturnPod(returnBincode, requestWbCode);
        }
        return new Result();
    }

    /**
     * 要求强行按照出库进度出库时，校验出库数据
     *
     * @param outBoundRequestDTO
     * @param wbAgvTaskNo
     */
    private void caseForceKeepOutstockProcessThenCheckOutBoundData(OutBoundRequestDTO outBoundRequestDTO, String wbAgvTaskNo) {
        if (applicationProperties.getOutstock().isForceKeepOutstockProcess()) {
            List<AgvTaskDetailOutstockProcess> agvTaskDetailOutstockProcessList = agvTaskDetailOutstockProcessMapper.selectUnCompletedOutStockProcessByTaskNo(wbAgvTaskNo);
            outBoundRequestDTO.getData().stream().forEach(outBoundData -> {
                Stock stock = getStockByOutBoundData(outBoundRequestDTO.getAreaCode(), outBoundData);
                List<AgvTaskDetailOutstockProcess> conOutBoundRecordProcessList = agvTaskDetailOutstockProcessList.stream().filter(agv -> agv.getStkCode().equals(stock.getStkCode())).collect(Collectors.toList());
                logger.info("出库确认stock stkCode:{}", stock.getStkCode());
                Preconditions.checkBusinessError(conOutBoundRecordProcessList.size() == 0, "出库确认信息未找到对应的出库推荐方案，请根据推荐方案拿货");
                Preconditions.checkBusinessError(conOutBoundRecordProcessList.size() > 1, "单条出库确认信息未定位到多个出库推荐方案，异常请联系管理员确认");
                AgvTaskDetailOutstockProcess conOutBoundRecordProcess = conOutBoundRecordProcessList.get(0);
                if (conOutBoundRecordProcess.getPreSnFlag().equals(PRE_SN)) {
                    List<String> processCalSns = ListUtils.convertStringToList(conOutBoundRecordProcess.getCalOutstockSns());
                    List<String> processAlreadyConfirmSns = ListUtils.convertStringToList(conOutBoundRecordProcess.getActualOutstockSns());
                    processCalSns.removeAll(processAlreadyConfirmSns);
                    Preconditions.checkBusinessError(!processCalSns.equals(outBoundData.getSn()), "出库方案为指定SN出库，上报SN与方案不符，请确认");
                } else {
                    BigDecimal remainQty = conOutBoundRecordProcess.getCalOutstockQty().subtract(conOutBoundRecordProcess.getActualOutstockQty());
                    BigDecimal outQty = returnOutBoundNum(outBoundData.getOutQty(), outBoundData.getSn());
                    Preconditions.checkBusinessError(outQty.compareTo(remainQty) != 0, "出库方案出库数量与本次出库数量不符，请确认。");
                }

            });
        }

    }

    /**
     * 根据出库确认数据获取库存信息
     *
     * @param requestAreaCode
     * @param data
     * @return
     */
    private Stock getStockByOutBoundData(String requestAreaCode, OutBoundDataDTO data) {
        //定位唯一库存
        StockQueryDTO stockQuery = new StockQueryDTO();
        stockQuery.setCargoOwner(data.getCargoOwner());
        stockQuery.setMatCode(data.getMatCode());
        stockQuery.setBincode(data.getBincode());
        stockQuery.setStkCharacter1(data.getStkCharacter1());
        stockQuery.setStkCharacter2(data.getStkCharacter2());
        stockQuery.setStkCharacter3(data.getStkCharacter3());
        stockQuery.setStkCharacter4(data.getStkCharacter4());
        stockQuery.setStkCharacter5(data.getStkCharacter5());
        stockQuery.setStgAreaCode(requestAreaCode);
        stockQuery.setBatchNum(data.getBatchNum());
        stockQuery.setSpecCode(data.getSpecCode());
        stockQuery.setContainerCode(data.getContainerCode());
        logger.info("出库查询库存，stockQueryDto,{}", stockQuery.toString());
        Stock stock = stockMapper.selectStockByStockCharacter(stockQuery);
        Preconditions.checkBusinessError(stock == null, "提报信息未定义到唯一库存，请确认参数");
        return stock;
    }

    /**
     * 判断是否为小车离开的出库确认
     *
     * @param requestDTO
     * @return
     */
    private boolean isAgvLeaveOutBound(OutBoundRequestDTO requestDTO) {
        return requestDTO.getAgvNextAction().equals(AGV_LEAVE);
    }


    /**
     * 更新出库进度
     *
     * @param updateOustockProcessDTOList
     */
    private void updateOustockProcess(List<UpdateOustockProcessDTO> updateOustockProcessDTOList) {
        if (applicationProperties.getOutstock().isNoticeOutstockProcess()) {
            logger.info("同步出库进度");
            updateOustockProcessDTOList.stream().forEach(processData -> {
                AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = agvTaskDetailOutstockProcessMapper.selectOutstockProcessByTaskNoAndBinCodeAndStkCode(processData.getTaskNo(), processData.getBinCode(), processData.getStkCode());
                logger.info("定位进度信息，任务号:{},仓位号:{},库存编号:{}", processData.getTaskNo(), processData.getBinCode(), processData.getStkCode());
                if (agvTaskDetailOutstockProcess != null) {
                    BigDecimal resulOutstockQty = agvTaskDetailOutstockProcess.getActualOutstockQty().add(processData.getOutStorageNum());
                    agvTaskDetailOutstockProcess.setActualOutstockQty(resulOutstockQty);
                    agvTaskDetailOutstockProcess.setLastModifiedTime(new Date());
                    if (agvTaskDetailOutstockProcess.getPreSnFlag().equals(PRE_SN)) {
                        String totalActualOutSn = agvTaskDetailOutstockProcess.getActualOutstockSns() + processData.getActualOutSns();
                        agvTaskDetailOutstockProcess.setActualOutstockSns(totalActualOutSn);
                    }
                    agvTaskDetailOutstockProcessMapper.updateByPrimaryKeySelective(agvTaskDetailOutstockProcess);
                }
            });
        }
    }

    /**
     * 调用回库方法
     *
     * @param returnBincode
     * @param wbCode
     */
    private void outboundReturnPod(String returnBincode, String wbCode) {
        ReturnPodDTO returnPodDTO = new ReturnPodDTO();
        returnPodDTO.setWbCode(wbCode);
        returnPodDTO.setTaskType(OUTSTOCK_TASK.getType());
        returnPodDTO.setBincode(returnBincode);
        IReturnPodService.returnPodRequest(returnPodDTO);
    }

    /**
     * 出库记录
     *
     * @param dataDTO
     * @param orderMatGenCode
     * @param requestAreaCode
     * @param stock
     * @param requestUserId
     */
    private void recordOutStockRecode(OutBoundDataDTO dataDTO, String orderMatGenCode, String requestAreaCode, Stock stock, String requestUserId, String srcBizReqCode) {
        boolean isSnOut = false;
        MatConfigRelation matConfigRelation = matConfigRelationMapper.selectByOrderMatGenCode(orderMatGenCode);
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(requestAreaCode, NOT_DELETED.getStatus());
        BigDecimal outQty = returnOutBoundNum(dataDTO.getOutQty(), dataDTO.getSn());
        OutstockRecord outstockRecord = new OutstockRecord();
        outstockRecord.setOutstkType(NORMAL_OUT);
        outstockRecord.setStkCode(stock.getStkCode());
        if (matConfigRelation != null) {
            outstockRecord.setConfigCode(matConfigRelation.getConfigCode());
        }
        outstockRecord.setBizOrderCode(dataDTO.getBizOrderCode());
        outstockRecord.setBizOrderItem(dataDTO.getSrcOrderItem());
        outstockRecord.setStkCharacter1(dataDTO.getStkCharacter1());
        outstockRecord.setStkCharacter2(dataDTO.getStkCharacter2());
        outstockRecord.setStkCharacter3(dataDTO.getStkCharacter3());
        outstockRecord.setStkCharacter4(dataDTO.getStkCharacter4());
        outstockRecord.setStkCharacter5(dataDTO.getStkCharacter5());
        outstockRecord.setCargoOwner(dataDTO.getCargoOwner());
        outstockRecord.setMatCode(dataDTO.getMatCode());
        outstockRecord.setBincode(dataDTO.getBincode());
        if (dataDTO.getSn() != null && dataDTO.getSn().size() != 0) {
            outstockRecord.setOutstockType(OUT_BY_SN);
            isSnOut = true;
        } else {
            outstockRecord.setOutstockType(OUT_BY_NUM);
        }
        outstockRecord.setTotalQty(outQty);
        outstockRecord.setStgAreaCode(requestAreaCode);
        outstockRecord.setWhCode(baseWhArea.getWhCode());
        outstockRecord.setBatchNum(dataDTO.getBatchNum());
        outstockRecord.setSpeStkFlag(stock.getSpeStkFlag());
        outstockRecord.setSpeStkCode(stock.getSpeStkCode());
        outstockRecord.setFreezeFlag(stock.getFreezeFlag());
        outstockRecord.setContainerCode(stock.getContainerCode());
        outstockRecord.setContainerFlag(stock.getContainerFlag());
        outstockRecord.setSkuQty(stock.getSkuQty());
        outstockRecord.setBlkReaCode(stock.getBlkReaCode());
        outstockRecord.setBumCode(stock.getBumCode());
        outstockRecord.setMatWeight(stock.getMatWeight());
        outstockRecord.setWeightUnit(stock.getWeightUnit());
        outstockRecord.setPlantCode(stock.getPlantCode());
        outstockRecord.setSrcBizReqCode(srcBizReqCode);
        outstockRecord.setCreatedTime(new Date());
        outstockRecord.setSrcUserCode(requestUserId);
        outstockRecord.setSpecCode(stock.getSpecCode());
        int id = outstockRecordMapper.insertSelective(outstockRecord);
        if (isSnOut) {
            List<OutstockRecordDetail> insertDetailList = new ArrayList<>();
            dataDTO.getSn().stream().forEach(sn -> {
                OutstockRecordDetail recordDetail = new OutstockRecordDetail();
                recordDetail.setOutstockRecordId(id);
                recordDetail.setStkCode(stock.getStkCode());
                recordDetail.setBincode(dataDTO.getBincode());
                recordDetail.setSn(sn);
                insertDetailList.add(recordDetail);
            });
            outstockRecordDetailMapper.insertList(insertDetailList);
        }
    }

    /**
     * 根据数量和SN信息，返回出库数量
     *
     * @param outQty
     * @param sn
     * @return
     */
    private BigDecimal returnOutBoundNum(BigDecimal outQty, List<String> sn) {
        BigDecimal outStorageNum = new BigDecimal(0);
        if (sn != null && sn.size() != 0) {
            outStorageNum = new BigDecimal(sn.size());
        } else {
            outStorageNum = outQty;
        }
        return outStorageNum;
    }

    /**
     * 生成虚拟单据
     *
     * @param bizVirtualOrderCode
     * @param request
     */
    private void outBoundNewVirtualOrder(String bizVirtualOrderCode, OutBoundRequestDTO request) {
        boolean newInsertOrder = false;
        if (Strings.isNullOrEmpty(bizVirtualOrderCode)) {
            //呼叫时未生成虚拟单
            bizVirtualOrderCode = UUID.randomUUID().toString().replace("-", "");
            newInsertOrder = true;
        }

        String requestAreaCode = request.getAreaCode();
        InitOutStockOrderRequestDTO createOutStockOrderDTO = new InitOutStockOrderRequestDTO();
        List<InitOutstockOrderDataDTO> createOutOrderList = new ArrayList<>();
        for (OutBoundDataDTO outBoundData : request.getData()) {
            OrderMat orderMat = orderMatMapper.selectByBizOrderCodeAndSrcOrderItem(bizVirtualOrderCode, outBoundData.getMatCode());
            BigDecimal outNum = returnOutBoundNum(outBoundData.getOutQty(), outBoundData.getSn());
            if (newInsertOrder || orderMat == null) {
                //创建出库单
                InitOutstockOrderDataDTO data = new InitOutstockOrderDataDTO();
                data.setBizOrderCode(bizVirtualOrderCode);
                data.setSrcOrderItem(outBoundData.getMatCode());
                data.setMatCode(outBoundData.getMatCode());
                data.setOutQty(outNum);
                data.setBatchNum(outBoundData.getBatchNum());
                data.setCargoOwner(outBoundData.getCargoOwner());
                data.setAreaCode(requestAreaCode);
                data.setOutUnit(PACKAGE_UNIT.getCode());
                data.setPreSnFlag(NOT_PRE_SN);
                data.setOrderType(VIRTUAL_OUT_ORDER);
                createOutOrderList.add(data);
            }
        }
        if (createOutOrderList.size() != 0) {
            createOutStockOrderDTO.setData(createOutOrderList);
            IOutstockBizOrderService.syncOutstockOrderInfo(createOutStockOrderDTO);
        }
    }


    /**
     * 更新出库单信息
     *
     * @param bizOrderCode
     * @param srcOrderItem
     * @param outStorageNum
     */
    private OrderMat updateOrderMatOutInfo(String bizOrderCode, String srcOrderItem, BigDecimal outStorageNum) {
        OrderMat orderMat = orderMatMapper.selectByBizOrderCodeAndSrcOrderItem(bizOrderCode, srcOrderItem);
        BigDecimal totalOutStorageNum = orderMat.getOutStorageNum().add(outStorageNum);
        orderMat.setOutStorageNum(totalOutStorageNum);
        BigDecimal orderOutNum = orderMat.getOutQty();
        boolean orderEndOut = totalOutStorageNum.compareTo(orderOutNum) == 1 || totalOutStorageNum.compareTo(orderOutNum) == 0;
        if (orderEndOut) {
            orderMat.setOrderMatStatus(ORDER_END_OUT);
        } else {
            orderMat.setOrderMatStatus(ORDER_START_OUT);
        }
        orderMatMapper.updateByPrimaryKeySelective(orderMat);

        return orderMat;
    }

    /**
     * 校验出库确认参数有效性
     *
     * @param request
     */
    private void checkOutBoundConfirmParam(OutBoundRequestDTO request) {
        boolean ifRequiredWbCode = ICommonService.IsOpenWbCode();
        boolean lockWbCode = ifRequiredWbCode && Strings.isNullOrEmpty(request.getWbCode());
        Preconditions.checkBusinessError(lockWbCode, "请指定点位信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getAreaCode()), "缺少库区信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getOutCallType()), "缺少呼叫类型");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getAgvNextAction()), "未指定确认后小车动作");
        Preconditions.checkBusinessError(request.getData().size() == 0, "缺少DATA信息");
        request.getData().stream().forEach(data -> {
            String dataBincode = data.getBincode();
            String dataPodCode = data.getBincode().substring(0, 6);
            String dataOrderNo = data.getBizOrderCode();
            String dataOrderItem = data.getSrcOrderItem();
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(dataBincode), "未指定确认货架");
            BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByBincodeAndValidFlagAndDeleteFlag(dataBincode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(baseBincodeDetail == null, "托盘:" + dataBincode + "未初始化");
            boolean isNotSameAreaCode = !baseBincodeDetail.getAreaCode().equals(request.getAreaCode());
            Preconditions.checkBusinessError(isNotSameAreaCode, "托盘:" + dataBincode + "所在库区与操作库区不用，请确认");
            List<WbTaskDetail> unCompletedTask = wbTaskDetailMapper.selectCountUnCompletedTaskByPodCode(dataPodCode);
            Preconditions.checkBusinessError(unCompletedTask.size() == 0, "货架:" + dataPodCode + "无待完成任务");
            boolean lockOrderInfo = request.getOutCallType().equals(ORDER_CALL) && Strings.isNullOrEmpty(dataOrderNo) && Strings.isNullOrEmpty(dataOrderItem);
            Preconditions.checkBusinessError(lockOrderInfo, "订单出库需上报出库单信息");
            if (request.getOutCallType().equals(ORDER_CALL)) {
                OrderMat orderMat = orderMatMapper.selectByBizOrderCodeAndSrcOrderItem(dataOrderNo, dataOrderItem);
                Preconditions.checkBusinessError(orderMat == null, "订单号：" + dataOrderNo + "订单行号：" + dataOrderItem + "未在IWCS中同步，请确认单号是否正确");
            }
            boolean lockOutBoundQty = (data.getOutQty() == null || data.getOutQty().compareTo(BigDecimal.ZERO) == 0) && (data.getSn() == null || data.getSn().size() == 0);
            Preconditions.checkBusinessError(lockOutBoundQty, "缺少出库数量信息，请指定数量或上报SN信息");
        });
    }

}

package com.wisdom.iwcs.service.outstock.outstockImpl;

import com.google.common.base.Strings;
import com.wisdom.base.annotation.LogExecuteParamsAndTimeRecord;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.ListUtils;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.commonDto.fliterCondition.stockCondition.OutstockCalConditon;
import com.wisdom.iwcs.commonDto.strategy.PodStrategyEnum;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.base.dto.WbcodeSameTypeTaskDTO;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.outstock.dto.*;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.iwcs.mapper.outstock.OrderMatMapper;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskDetailOutstockProcessMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskOutstockStockMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IGetOutPodService;
import com.wisdom.iwcs.service.common.IStockCal;
import com.wisdom.iwcs.service.outstock.IOutStockCallOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockCallTypeConstants.*;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockOrderConstants.OrderTypeConstants.VIRTUAL_OUT_ORDER;
import static com.wisdom.iwcs.common.utils.outStockUtils.OutStockTypeConstants.OUT_BY_NUM;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.PRE_SN;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.OUTSTOCK_TASK;
import static com.wisdom.iwcs.common.utils.unitUtils.OutUnitEnum.PACKAGE_UNIT;

/**
 * 出库呼叫
 *
 * @author cecilia.yang
 * @version 2019/02/25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@LogExecuteParamsAndTimeRecord
public class OutStockCallOutService implements IOutStockCallOutService {
    private final Logger logger = LoggerFactory.getLogger(OutStockCallOutService.class);
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private OrderMatMapper orderMatMapper;
    @Autowired
    private IGetOutPodService IGetOutPodService;
    @Autowired
    private com.wisdom.iwcs.service.outstock.IOutstockBizOrderService IOutstockBizOrderService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    IStockCal iStockCal;
    @Autowired
    private AgvTaskOutstockStockMapper agvTaskOutstockStockMapper;
    @Autowired
    private AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper;


    /**
     * 出库呼叫
     *
     * @param requestDTO
     * @return
     */
    @Override
    public Result outStockCallOut(OutStockCallRequestDTO requestDTO) {
        checkOutStockCallParam(requestDTO);
        WbcodeSameTypeTaskDTO wbcodeSameTypeTaskDTO = ICommonService.checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType(requestDTO.getWbCode(), OUTSTOCK_TASK.getType());
        boolean isHaveSameTypeTask = wbcodeSameTypeTaskDTO.isHaveSameTypeTask();
        if (!isHaveSameTypeTask) {
            return newCreateOutStockTask(requestDTO);
        } else {
            return outstockCallRepeatTrigger(requestDTO, wbcodeSameTypeTaskDTO.getWbAgvTask());
        }
    }

    /**
     * 手动补充呼叫
     *
     * @param requestDTO
     * @param wbAgvTask
     * @return
     */
    private Result outstockCallRepeatTrigger(OutStockCallRequestDTO requestDTO, WbAgvTask wbAgvTask) {
        String newSrcReqCode = requestDTO.getSrcReqCode();
        String existsSrcReqCode = wbAgvTask.getSrcReqCode();
        if (!newSrcReqCode.equals(existsSrcReqCode)) {
            return new Result(400, "当前点位已存在呼叫号为" + wbAgvTask.getSrcReqCode() + "的出库任务，不允许累计呼叫");
        }

        if (!applicationProperties.getOutstock().isAllowHandOutstockSupplyCall()) {
            return new Result(400, "当前点位已存在呼叫任务，系统不允许手动触发补充呼叫逻辑，请等待");
        }

        List<AgvTaskOutstockStock> missingOutstockInfos = agvTaskOutstockStockMapper.selectMissingOutstockStockInfoByTaskNo(wbAgvTask.getTaskNo());
        if (missingOutstockInfos.size() == 0) {
            return new Result(400, "出库任务已全部呼叫，无需补充货架，请耐心等待");
        }
        List<String> outSns = new ArrayList<>();
        boolean havePreSn = false;
        List<OutstockCalPodParamDTO> outstockCalPodParamDTOS = new ArrayList<>();
        for (AgvTaskOutstockStock missingInfo : missingOutstockInfos) {
            OutstockCalPodParamDTO outstockCalPodParamDTO = new OutstockCalPodParamDTO();
            outstockCalPodParamDTO.setStgAreaCode(missingInfo.getStgAreaCode());
            outstockCalPodParamDTO.setCargoOwner(missingInfo.getCargoOwner());
            outstockCalPodParamDTO.setMatCode(missingInfo.getMatCode());
            outstockCalPodParamDTO.setBatchNum(missingInfo.getBatchNum());
            outstockCalPodParamDTO.setStkCharacter1(missingInfo.getStkCharacter1());
            outstockCalPodParamDTO.setStkCharacter2(missingInfo.getStkCharacter2());
            outstockCalPodParamDTO.setStkCharacter3(missingInfo.getStkCharacter3());
            outstockCalPodParamDTO.setStkCharacter4(missingInfo.getStkCharacter4());
            outstockCalPodParamDTO.setStkCharacter5(missingInfo.getStkCharacter5());
            outstockCalPodParamDTO.setOutQty(missingInfo.getMissingQty());
            outstockCalPodParamDTO.setContainerCode(missingInfo.getContainerCode());
            outstockCalPodParamDTO.setSpecCode(missingInfo.getSpecCode());
            if (missingInfo.getPreSnFlag().equals(PRE_SN)) {
                havePreSn = true;
                List<String> missingSns = ListUtils.convertStringToList(missingInfo.getMissingSns());
                outSns.addAll(missingSns);
            }
            outstockCalPodParamDTOS.add(outstockCalPodParamDTO);
        }
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        outstockCalConditon.setOutstockCalPodParamDTOS(outstockCalPodParamDTOS);
        outstockCalPodParamDTOS.get(0).setPointSns(outSns);
        outstockCalPodParamDTOS.get(0).setHavePreSn(havePreSn);
        List<OutstockCalPodResultDTO> resultList = iStockCal.calStockByStockCondition(outstockCalConditon);
        List<OutstockCalPodResultDataDTO> buildNotifyOutstockProcessDetail = new ArrayList<>();

        List<AgvTaskDetailOutstockProcess> unCompletedOutStockProcessList = agvTaskDetailOutstockProcessMapper.selectUnCompletedOutStockProcessByTaskNo(wbAgvTask.getTaskNo());
        unCompletedOutStockProcessList.stream().forEach(unCompletedOutStockProcess -> {
            OutstockCalPodResultDataDTO outstockCalPodResultDataDTO = new OutstockCalPodResultDataDTO();
            outstockCalPodResultDataDTO.setBincode(unCompletedOutStockProcess.getBinCode());
            outstockCalPodResultDataDTO.setStkCode(unCompletedOutStockProcess.getStkCode());
            BigDecimal finalOutQty = unCompletedOutStockProcess.getCalOutstockQty().subtract(unCompletedOutStockProcess.getActualOutstockQty());
            outstockCalPodResultDataDTO.setOutstockQty(finalOutQty);
            List<String> remainSns = returnRemainSn(unCompletedOutStockProcess);
            outstockCalPodResultDataDTO.setOutSns(remainSns);
            buildNotifyOutstockProcessDetail.add(outstockCalPodResultDataDTO);
        });


        return handleOutBincodeAndReturnResult(requestDTO, wbAgvTask.getBizOrderCode(), resultList, buildNotifyOutstockProcessDetail);
    }


    private List<String> returnRemainSn(AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess) {
        List<String> finalSns = new ArrayList<>();
        if (agvTaskDetailOutstockProcess.getPreSnFlag().equals(PRE_SN)) {
            finalSns = ListUtils.convertStringToList(agvTaskDetailOutstockProcess.getCalOutstockSns());
            List<String> outSns = ListUtils.convertStringToList(agvTaskDetailOutstockProcess.getActualOutstockSns());
            finalSns.removeAll(outSns);
        }
        return finalSns;
    }


    /**
     * 创建一个新的出库任务
     *
     * @param requestDTO
     * @return
     */
    private Result newCreateOutStockTask(OutStockCallRequestDTO requestDTO) {
        String callType = requestDTO.getCallType();
        //调用货架计算器
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        String virtualOrder = "";
        switch (callType) {
            case ORDER_CALL:
                outstockCalConditon = orderDetailCallCalculateOutstockQty(requestDTO);
                break;
            case WHOLE_ORDER_CALL:
                outstockCalConditon = wholeOrderCallCalculateOutstockQty(requestDTO);
                break;
            case NOT_ORDER_CALL:
                outstockCalConditon = notOrderCallCalculateOutstockQty(requestDTO);
                virtualOrder = notOrderCallCreateVirtualOutOrderAndReturnOrderNo(outstockCalConditon.getOutstockCalPodParamDTOS());
                break;
            default:
                throw new BusinessException("未定义的呼叫出库类型：" + callType);
        }
        // TODO: 2019/3/20 出库策略未定
        List<PodStrategyEnum> outstockCalPodStra = new ArrayList<>();
        outstockCalConditon.setPodStrategyEnums(outstockCalPodStra);
        List<OutstockCalPodResultDTO> resultList = iStockCal.calStockByStockCondition(outstockCalConditon);
        //处理结果
        return handleOutBincodeAndReturnResult(requestDTO, virtualOrder, resultList, new ArrayList<>());

    }

    /**
     * 统一处理货架出库、返回前端数据
     *
     * @param requestDTO
     * @param virtualOrder
     * @param resultList
     * @param buildNotifyOutstockProcessDetail
     * @return
     */
    private Result handleOutBincodeAndReturnResult(OutStockCallRequestDTO requestDTO, String virtualOrder, List<OutstockCalPodResultDTO> resultList, List<OutstockCalPodResultDataDTO> buildNotifyOutstockProcessDetail) {
        List<String> outBincodes = new ArrayList<>();
        resultList.stream().forEach(result -> {
            if (result.getData().size() != 0) {
                List<String> resultBincodes = result.getData().stream().map(OutstockCalPodResultDataDTO::getBincode).collect(Collectors.toList());
                outBincodes.addAll(resultBincodes);
                buildNotifyOutstockProcessDetail.addAll(result.getData());
            }
        });
        Preconditions.checkBusinessError(outBincodes.size() == 0, "未定位到可出库货架，请确定库内是否有库存");
        List<SyncOuterTaskProcessDTO> syncOutstockProcessInfo = new ArrayList<>();
        boolean syncOuterProcess = applicationProperties.getOutstock().isNoticeOutstockProcess();
        if (syncOuterProcess) {
            syncOutstockProcessInfo = buildSyncOuterTaskProcessInfo(buildNotifyOutstockProcessDetail);
        }
        //创建出库任务
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setBincodes(outBincodes);
        getOutPodDTO.setWbCode(requestDTO.getWbCode());
        getOutPodDTO.setTaskType(OUTSTOCK_TASK.getType());
        getOutPodDTO.setSrcClientCode(requestDTO.getSrcClientType());
        getOutPodDTO.setSrcReqCode(requestDTO.getSrcReqCode());
        getOutPodDTO.setSrcUserCode(requestDTO.getSrcUserCode());
        getOutPodDTO.setBizOrderCode(virtualOrder);
        getOutPodDTO.setTaskProcess(resultList);
        IGetOutPodService.getOutPod(getOutPodDTO);
        return new Result(200, "success!", syncOutstockProcessInfo);
    }


    /**
     * 组装同步前端手持进度
     *
     * @param outDetailList
     * @return
     */
    private List<SyncOuterTaskProcessDTO> buildSyncOuterTaskProcessInfo(List<OutstockCalPodResultDataDTO> outDetailList) {
        List<SyncOuterTaskProcessDTO> syncInfoList = new ArrayList<>();
        outDetailList.stream().forEach(outDetail -> {
            Stock stock = stockMapper.selectStockByStkCodeAndDeleteFlag(outDetail.getStkCode(), NOT_DELETED.getStatus());
            SyncOuterTaskProcessDTO syncOuterTaskProcessDTO = new SyncOuterTaskProcessDTO();
            syncOuterTaskProcessDTO.setBincode(outDetail.getBincode());
            syncOuterTaskProcessDTO.setTakeQty(outDetail.getOutstockQty());
            syncOuterTaskProcessDTO.setOutSns(outDetail.getOutSns());
            syncOuterTaskProcessDTO.setBatchNum(stock.getBatchNum());
            syncOuterTaskProcessDTO.setCargoOwner(stock.getCargoOwner());
            syncOuterTaskProcessDTO.setMatCode(stock.getMatCode());
            syncOuterTaskProcessDTO.setStkCharacter1(stock.getStkCharacter1());
            syncOuterTaskProcessDTO.setStkCharacter2(stock.getStkCharacter2());
            syncOuterTaskProcessDTO.setStkCharacter3(stock.getStkCharacter3());
            syncOuterTaskProcessDTO.setStkCharacter4(stock.getStkCharacter4());
            syncOuterTaskProcessDTO.setStkCharacter5(stock.getStkCharacter5());
            syncInfoList.add(syncOuterTaskProcessDTO);
        });
        return syncInfoList;
    }

    /**
     * 无订单呼叫生成虚拟订单
     *
     * @param virtualOrderInfoList
     * @return
     */
    private String notOrderCallCreateVirtualOutOrderAndReturnOrderNo(List<OutstockCalPodParamDTO> virtualOrderInfoList) {
        String virtualOrderNo = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        InitOutStockOrderRequestDTO createOutStockOrderDTO = new InitOutStockOrderRequestDTO();
        List<InitOutstockOrderDataDTO> createOutOrderList = new ArrayList<>();
        virtualOrderInfoList.stream().forEach(virtualOrderInfo -> {
            InitOutstockOrderDataDTO data = new InitOutstockOrderDataDTO();
            data.setBizOrderCode(virtualOrderNo);
            data.setSrcOrderItem(virtualOrderInfo.getMatCode());
            data.setMatCode(virtualOrderInfo.getMatCode());
            data.setOutQty(virtualOrderInfo.getOutQty());
            data.setBatchNum(virtualOrderInfo.getBatchNum());
            data.setCargoOwner(virtualOrderInfo.getCargoOwner());
            data.setAreaCode(virtualOrderInfo.getStgAreaCode());
            data.setOutUnit(PACKAGE_UNIT.getCode());
            data.setPreSnFlag(OUT_BY_NUM);
            data.setOrderType(VIRTUAL_OUT_ORDER);
            createOutOrderList.add(data);
        });
        createOutStockOrderDTO.setData(createOutOrderList);
        IOutstockBizOrderService.syncOutstockOrderInfo(createOutStockOrderDTO);
        return virtualOrderNo;
    }

    /**
     * 未指定单号呼叫出库，计算库存属性和数量
     *
     * @param requestDTO
     * @return
     */
    private OutstockCalConditon notOrderCallCalculateOutstockQty(OutStockCallRequestDTO requestDTO) {
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        List<OutstockCalPodParamDTO> calPodParamDTOList = new ArrayList<>();
        List<Object> a = new ArrayList<>();

        String requestAreaCode = requestDTO.getAreaCode();
        requestDTO.getData().stream().forEach(data -> {
            OutstockCalPodParamDTO outstockCalPodParamDTO = new OutstockCalPodParamDTO();
            outstockCalPodParamDTO.setStgAreaCode(requestAreaCode);
            outstockCalPodParamDTO.setCargoOwner(data.getCargoOwner());
            outstockCalPodParamDTO.setMatCode(data.getMatCode());
            outstockCalPodParamDTO.setBatchNum(data.getBatchNum());
            outstockCalPodParamDTO.setStkCharacter1(data.getStkCharacter1());
            outstockCalPodParamDTO.setStkCharacter2(data.getStkCharacter2());
            outstockCalPodParamDTO.setStkCharacter3(data.getStkCharacter3());
            outstockCalPodParamDTO.setStkCharacter4(data.getStkCharacter4());
            outstockCalPodParamDTO.setStkCharacter5(data.getStkCharacter5());
            outstockCalPodParamDTO.setSpecCode(data.getSpecCode());
            outstockCalPodParamDTO.setContainerCode(data.getContainerCode());
            if (calPodParamDTOList.contains(outstockCalPodParamDTO)) {
                int i = calPodParamDTOList.indexOf(outstockCalPodParamDTO);
                BigDecimal totalOutQty = data.getCallOutQty().add(calPodParamDTOList.get(i).getOutQty());
                calPodParamDTOList.get(i).setOutQty(totalOutQty);
            } else {
                outstockCalPodParamDTO.setOutQty(data.getCallOutQty());
                calPodParamDTOList.add(outstockCalPodParamDTO);
            }
        });
        Preconditions.checkBusinessError(calPodParamDTOList.size() == 0, "无法定位到出库要求，请检查参数");
        outstockCalConditon.setOutstockCalPodParamDTOS(calPodParamDTOList);
        return outstockCalConditon;
    }

    /**
     * 整单呼叫计算
     *
     * @param requestDTO
     * @return
     */
    private OutstockCalConditon wholeOrderCallCalculateOutstockQty(OutStockCallRequestDTO requestDTO) {
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        String requestAreaCode = requestDTO.getAreaCode();
        List<String> bizOrderCodes = requestDTO.getData().stream().map(OutStockCallDataDTO::getBizOrderCode).distinct().collect(Collectors.toList());
        List<OutstockCalPodParamDTO> stockParamData = orderMatMapper.selectNotPreSnReadyOutstockCharacterByBizWholeOrderNoOrBizOrderCodesAndAreaCode(null, bizOrderCodes, requestAreaCode);
        Preconditions.checkBusinessError(stockParamData.size() == 0, "无法定位到出库要求，请检查参数");
        List<String> outSns = orderMatMapper.selectOutSnByBizWholeOrderNoOrBizOrderCodesAndAreaCode(null, bizOrderCodes, requestAreaCode);
        outstockCalConditon.setOutstockCalPodParamDTOS(stockParamData);
        if (outSns.size() != 0) {
            stockParamData.get(0).setPointSns(outSns);
            stockParamData.get(0).setHavePreSn(true);
        }
        return outstockCalConditon;
    }

    /**
     * 按订单明细呼叫的时候，返回计算货架的库存特征和数量
     *
     * @param requestDTO
     * @return
     */
    private OutstockCalConditon orderDetailCallCalculateOutstockQty(OutStockCallRequestDTO requestDTO) {
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        String requestAreaCode = requestDTO.getAreaCode();
        List<String> wholeOrderNos = new ArrayList<>();
        requestDTO.getData().stream().forEach(data -> {
            String wholeOrderNo = data.getBizOrderCode() + data.getSrcOrderItem();
            wholeOrderNos.add(wholeOrderNo);
        });
        List<OutstockCalPodParamDTO> stockParamData = orderMatMapper.selectNotPreSnReadyOutstockCharacterByBizWholeOrderNoOrBizOrderCodesAndAreaCode(wholeOrderNos, null, requestAreaCode);
        Preconditions.checkBusinessError(stockParamData.size() == 0, "无法定位到出库要求，请检查参数");
        List<String> outSns = orderMatMapper.selectOutSnByBizWholeOrderNoOrBizOrderCodesAndAreaCode(wholeOrderNos, null, requestAreaCode);
        outstockCalConditon.setOutstockCalPodParamDTOS(stockParamData);
        if (outSns.size() != 0) {
            stockParamData.get(0).setPointSns(outSns);
            stockParamData.get(0).setHavePreSn(true);
        }
        return outstockCalConditon;
    }

    /**
     * 校验参数有效性
     * 非订单呼叫校验哪些参数值？
     *
     * @param requestDTO
     */
    private void checkOutStockCallParam(OutStockCallRequestDTO requestDTO) {
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getWbCode()), "未指定出库点位");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getAreaCode()), "未指定出库库区信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getCallType()), "未指定呼叫出库类型");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getSrcReqCode()), "未指定呼叫用户");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestDTO.getSrcClientType()), "未上报客户端类型");
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(requestDTO.getWbCode(), ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWb == null, "出库点位:" + requestDTO.getWbCode() + "在系统中不存在，请确认");
        boolean isNotSameAreaCode = !baseWb.getAreaCode().equals(requestDTO.getAreaCode());
        Preconditions.checkBusinessError(isNotSameAreaCode, "出库点位:" + requestDTO.getWbCode() + "与选择的出库库区不同，不允许跨库区呼叫！");
        requestDTO.getData().stream().forEach(data -> {
            boolean wholeOrderCallMissingParam = requestDTO.getCallType().equals(WHOLE_ORDER_CALL) && Strings.isNullOrEmpty(data.getBizOrderCode());
            Preconditions.checkBusinessError(wholeOrderCallMissingParam, "整单呼叫缺少订单号");
            boolean orderCallMissingParam = requestDTO.getCallType().equals(ORDER_CALL) && (Strings.isNullOrEmpty(data.getBizOrderCode()) || Strings.isNullOrEmpty(data.getSrcOrderItem()));
            Preconditions.checkBusinessError(orderCallMissingParam, "按单呼叫缺少订单号或订单行号");
        });
    }
}

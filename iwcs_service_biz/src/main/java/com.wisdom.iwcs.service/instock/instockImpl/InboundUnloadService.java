package com.wisdom.iwcs.service.instock.instockImpl;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.ValidFlagEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.BaseMat;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.InstockRecord;
import com.wisdom.iwcs.domain.instock.InstockRecordDetail;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.DataInboundDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.InboundRequestDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.domain.stock.dto.StockQueryDTO;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseMatMapper;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.instock.InstockOrderDetailMapper;
import com.wisdom.iwcs.mapper.instock.InstockOrderMapper;
import com.wisdom.iwcs.mapper.instock.InstockRecordDetailMapper;
import com.wisdom.iwcs.mapper.instock.InstockRecordMapper;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.mapstruct.stock.StockMapStruct;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IReturnPodService;
import com.wisdom.iwcs.service.control.ICallOutByPodService;
import com.wisdom.iwcs.service.instock.ICycleCallPodService;
import com.wisdom.iwcs.service.instock.IInboundUnloadService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.stock.IStockService;
import com.wisdom.iwcs.service.stock.IStockSnService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.InStockConstants.OrderStatusConstants.ORDER_END_IN;
import static com.wisdom.iwcs.common.utils.InStockConstants.OrderStatusConstants.ORDER_START_IN;
import static com.wisdom.iwcs.common.utils.InStockConstants.cycleCallConstants.CYCLECALL;
import static com.wisdom.iwcs.common.utils.InStockConstants.freezeFlagConstants.FREEZE_IN;
import static com.wisdom.iwcs.common.utils.InStockConstants.instockOrderType.NORMALINSTOCK;
import static com.wisdom.iwcs.common.utils.InStockConstants.instockTypeConstants.NUM_IN;
import static com.wisdom.iwcs.common.utils.InStockConstants.instockTypeConstants.SN_IN;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.INSTOCK_TASK;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvAfterArrivalActionConstants.AGV_LEAVE;

/**
 * @Description: 入库确认
 * @Author: george
 * @CreateDate: 2019/2/28 9:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InboundUnloadService implements IInboundUnloadService {
    private final Logger logger = LoggerFactory.getLogger(InboundUnloadService.class);

    @Resource
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Resource
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private ICommonService ICommonService;
    @Resource
    private InstockOrderMapper instockOrderMapper;
    @Resource
    private InstockOrderDetailMapper instockOrderDetailMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private BaseMatMapper baseMatMapper;
    @Autowired
    private IStockService IStockService;
    @Autowired
    private IStockSnService IStockSnService;
    @Autowired
    private InstockRecordMapper instockRecordMapper;
    @Autowired
    private InstockRecordDetailMapper instockRecordDetailMapper;
    @Autowired
    private IReturnPodService IReturnPodService;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockMapStruct stockMapStruct;
    @Autowired
    private ICallOutByPodService ICallOutByPodService;
    @Autowired
    private ICycleCallPodService iCycleCallPodService;


    /**
     * 入库确认
     */
    @Override
    public Result inbound(InboundRequestDTO inboundRequestDTO) {
        checkInBoundConfirmParam(inboundRequestDTO);
        String requestAreaCode = inboundRequestDTO.getAreaCode();
        String reqCode = inboundRequestDTO.getReqcode();
        String userId = inboundRequestDTO.getUserId();
        List<String> binCodes = new ArrayList<>();
        inboundRequestDTO.getData().stream().forEach(data -> {
            String orderCode = "";
            BigDecimal inStockNum = returnInBoundNum(data.getInQty(), data.getSn());
            InstockOrderDetail instockOrderDetail = new InstockOrderDetail();
            if (StringUtils.isNotBlank(data.getOrderNo())) {
                instockOrderDetail = updateInstockOrderInfo(data.getOrderNo(), data.getSubOrderNo(), inStockNum);
            } else {
                instockOrderDetail = null;
            }
            BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(requestAreaCode, NOT_DELETED.getStatus());
            //如库存
            StockDTO stockDTO = insertStock(instockOrderDetail, data, inStockNum, baseWhArea);
            //写入记录
            recordInStockRecode(stockDTO, data.getSn(), userId, reqCode);
            binCodes.add(data.getBincode());
        });

        //默认小车离开
        if (inboundRequestDTO.getAgvNextAction().equals(AGV_LEAVE)) {
            List<String> returnBinCodes = ICommonService.distinctBinCodeByPodCode(binCodes);
            for (String binCode : returnBinCodes) {
                ReturnPodDTO returnPodDTO = new ReturnPodDTO();
                returnPodDTO.setBincode(binCode);
                returnPodDTO.setWbCode(inboundRequestDTO.getWbCode());
                returnPodDTO.setTaskType(INSTOCK_TASK.getType());
                IReturnPodService.returnPodRequest(returnPodDTO);
            }
        }

        //循环呼叫
        WbAgvTask wbAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(inboundRequestDTO.getWbCode());
        if (inboundRequestDTO.getLooplb().equals(CYCLECALL) || wbAgvTask.getCycletp().equals(CYCLECALL)) {
            iCycleCallPodService.cycleCallPods(inboundRequestDTO.getWbCode(), inboundRequestDTO.getAreaCode(), CYCLECALL);
        }
        return new Result();
    }

    /**
     * 写入库存
     */
    private StockDTO insertStock(InstockOrderDetail instockOrderDetail, DataInboundDTO data, BigDecimal inStockNum, BaseWhArea baseWhArea) {

        //查询仓位和sku一致，直接更新数量
        StockQueryDTO stockQueryDTO = new StockQueryDTO();
        stockQueryDTO.setBincode(data.getBincode());
        stockQueryDTO.setStgAreaCode(baseWhArea.getAreaCode());
        stockQueryDTO.setMatCode(data.getSku());
        Stock existStock = stockMapper.selectStockByStockCharacter(stockQueryDTO);
        if (existStock != null) {
            BigDecimal qty = existStock.getTotalQty().add(inStockNum);
            existStock.setInitQty(qty);
            existStock.setTotalQty(qty);
            StockDTO existStockDTO = stockMapStruct.toDto(existStock);
            IStockService.updateByPrimaryKeySelective(existStockDTO);
        }
        StockDTO stockDTO = new StockDTO();
        String stkCode = UUID.randomUUID().toString().replaceAll("-", "");
        stockDTO.setStkCode(stkCode);
        if (instockOrderDetail != null) {
            stockDTO.setOrderNo(instockOrderDetail.getOrderNo());
            stockDTO.setSubOrderNo(instockOrderDetail.getSubOrderNo());
        }
        stockDTO.setCargoOwner(data.getCargoOwner());
        stockDTO.setMatCode(data.getSku());
        stockDTO.setBincode(data.getBincode());
        stockDTO.setPodCode(data.getBincode().substring(0, 6));
        stockDTO.setInitQty(inStockNum);
        stockDTO.setTotalQty(inStockNum);
        stockDTO.setAvailableQty(inStockNum);
        stockDTO.setWhCode(baseWhArea.getWhCode());
        stockDTO.setLockQty(new BigDecimal(0));
        stockDTO.setFreezeFlag("0");
        stockDTO.setInvStat("0");
        stockDTO.setStgAreaCode(baseWhArea.getAreaCode());
        BaseMat mat = baseMatMapper.selectByMatCode(data.getSku());
        stockDTO.setBumCode(mat.getBumCode());
        stockDTO.setWeightUnit(mat.getWeightUnit());
        //暂时定净整
        stockDTO.setMatWeight(mat.getNetWeight());
        stockDTO.setBatchNum(data.getBatchNum());
        stockDTO.setPlantCode(data.getPlantCode());
        stockDTO.setDateLastPm(new Date());
        stockDTO.setStkCharacter1(data.getStkCharacter1());
        stockDTO.setStkCharacter2(data.getStkCharacter2());
        stockDTO.setStkCharacter3(data.getStkCharacter3());
        stockDTO.setStkCharacter4(data.getStkCharacter4());
        stockDTO.setStkCharacter5(data.getStkCharacter5());
        IStockService.insert(stockDTO);
        if (data.getSn() != null && data.getSn().size() != 0) {
            List<StockSnDTO> stockSnDTOS = new ArrayList<>();
            data.getSn().forEach(sn -> {
                StockSnDTO stockSnDTO = new StockSnDTO();
                stockSnDTO.setSn(sn);
                stockSnDTO.setStkCode(stkCode);
                stockSnDTOS.add(stockSnDTO);
            });
            IStockSnService.insertBatch(stockSnDTOS);
        }
        return stockDTO;
    }

    /**
     * 写入入库记录
     */
    private void recordInStockRecode(StockDTO stockDTO, List<String> sns, String userId, String reqCode) {
        InstockRecord instockRecord = new InstockRecord();
        instockRecord.setInstkBizType(NORMALINSTOCK);
        instockRecord.setStkCode(stockDTO.getStkCode());
        instockRecord.setOrderNo(stockDTO.getOrderNo());
        instockRecord.setSubOrderNo(stockDTO.getSubOrderNo());
        instockRecord.setMatCode(stockDTO.getMatCode());
        instockRecord.setCargoOwner(stockDTO.getCargoOwner());
        instockRecord.setBincode(stockDTO.getBincode());
        instockRecord.setTotalQty(stockDTO.getTotalQty());
        instockRecord.setWhCode(stockDTO.getWhCode());
        instockRecord.setAreaCode(stockDTO.getStgAreaCode());
        instockRecord.setBatchNum(stockDTO.getBatchNum());
        instockRecord.setWeightUnit(stockDTO.getWeightUnit());
        instockRecord.setMatWeight(stockDTO.getMatWeight());
        instockRecord.setBatchNum(stockDTO.getBatchNum());
        instockRecord.setPlantCode(stockDTO.getPlantCode());
        instockRecord.setSrcUserCode(userId);
        instockRecord.setSrcBizReqCode(reqCode);
        instockRecord.setFreezeFlag(FREEZE_IN);
        instockRecord.setCreatedTime(new Date());
        instockRecord.setCreatedBy(SecurityUtils.getCurrentUserId());
        if (sns != null && sns.size() != 0) {
            instockRecord.setInstockType(SN_IN);
        } else {
            instockRecord.setInstockType(NUM_IN);
        }
        Integer recordId = instockRecordMapper.insert(instockRecord);
        if (sns != null && sns.size() != 0) {
            List<InstockRecordDetail> instockRecordDetails = new ArrayList<>();
            sns.forEach(sn -> {
                InstockRecordDetail instockRecordDetail = new InstockRecordDetail();
                instockRecordDetail.setBincode(stockDTO.getBincode());
                instockRecordDetail.setStkCode(stockDTO.getStkCode());
                instockRecordDetail.setSn(sn);
                instockRecordDetail.setInstockRecordId(recordId);
                instockRecordDetails.add(instockRecordDetail);
            });
            instockRecordDetailMapper.insertList(instockRecordDetails);
        }


    }

    /**
     * 更新入库单明细
     */
    private InstockOrderDetail updateInstockOrderInfo(String orderNo, String subOrderNo, BigDecimal inStockNum) {
        InstockOrderDetail instockOrderDetail = instockOrderDetailMapper.selectInstockOrderDetail(orderNo, subOrderNo);
        boolean orderEndIn = inStockNum.compareTo(instockOrderDetail.getMatQty()) == 1 || inStockNum.compareTo(instockOrderDetail.getMatQty()) == 0;
        if (orderEndIn) {
            instockOrderDetail.setInStorageNum(inStockNum);
            instockOrderDetail.setOrderDetailStatus(ORDER_END_IN);
        } else {
            instockOrderDetail.setInStorageNum(inStockNum.add(instockOrderDetail.getInStorageNum()));
            instockOrderDetail.setOrderDetailStatus(ORDER_START_IN);
        }
        instockOrderDetail.setLastModifiedTime(new Date());
        instockOrderDetailMapper.updateByPrimaryKeySelective(instockOrderDetail);
        return instockOrderDetail;
    }


    /**
     * 根据数量和SN信息，返回入库数量
     *
     * @param inQty
     * @param sn
     * @return
     */
    private BigDecimal returnInBoundNum(BigDecimal inQty, List<String> sn) {
        BigDecimal inStockNum = new BigDecimal(0);
        if (sn != null && sn.size() != 0) {
            inStockNum = new BigDecimal(sn.size());
        } else {
            inStockNum = inQty;
        }
        return inStockNum;
    }

    /**
     * 校验入库确认传入参数
     */
    private void checkInBoundConfirmParam(InboundRequestDTO request) {
        boolean ifRequiredWbCode = ICommonService.IsOpenWbCode();
        boolean lockWbCode = ifRequiredWbCode && Strings.isNullOrEmpty(request.getWbCode());
        Preconditions.checkBusinessError(lockWbCode, "请指定点位信息");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getAgvNextAction()), "未指定确认后小车动作");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(request.getAreaCode()), "缺少库区信息");
        Preconditions.checkBusinessError(request.getData().size() == 0, "缺少DATA信息");
        request.getData().stream().forEach(data -> {
            String dataBincode = data.getBincode();
            String dataPodCode = data.getBincode().substring(0, 6);
            String dataOrderNo = data.getOrderNo();
            BaseMat mat = baseMatMapper.selectByMatCode(data.getSku());
            Preconditions.checkBusinessError(mat == null, "物料:" + data.getSku() + "未在IWCS中同步，请物料编号是否正确");
            Preconditions.checkBusinessError(Strings.isNullOrEmpty(dataBincode), "未指定确认货架");
            BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByBincodeAndValidFlagAndDeleteFlag(dataBincode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
            Preconditions.checkBusinessError(baseBincodeDetail == null, "托盘:" + dataBincode + "未初始化");
            boolean isNotSameAreaCode = !baseBincodeDetail.getAreaCode().equals(request.getAreaCode());
            Preconditions.checkBusinessError(isNotSameAreaCode, "托盘:" + dataBincode + "所在库区与操作库区不用，请确认");
            List<WbTaskDetail> unCompletedTask = wbTaskDetailMapper.selectCountUnCompletedTaskByPodCode(dataPodCode);
            Preconditions.checkBusinessError(unCompletedTask.size() == 0, "货架:" + dataPodCode + "无待完成任务");
            boolean lockOutBoundQty = data.getInQty() == null || data.getInQty().compareTo(BigDecimal.ZERO) == 0;
            Preconditions.checkBusinessError(lockOutBoundQty, "缺少入库数量信息，请指定数量");
            if (StringUtils.isNotBlank(data.getOrderNo())) {
                InstockOrderDetail instockOrder = instockOrderDetailMapper.selectInstockOrderDetail(data.getOrderNo(), data.getSubOrderNo());
                Preconditions.checkBusinessError(instockOrder == null, "订单号：" + dataOrderNo + "未在IWCS中同步，请确认单号是否正确");
                Preconditions.checkBusinessError(data.getInQty().compareTo(instockOrder.getInStorageNum()) == 1, "订单：" + dataOrderNo + "不能超出数量入库");
            }
        });
    }
}

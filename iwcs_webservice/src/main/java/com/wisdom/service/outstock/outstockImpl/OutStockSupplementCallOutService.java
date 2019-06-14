package com.wisdom.service.outstock.outstockImpl;

import com.google.common.base.Strings;
import com.wisdom.config.ApplicationProperties;
import com.wisdom.event.PodTaskFinishedEvent;
import com.wisdom.event.PodTaskFinishedEventInfos;
import com.wisdom.iwcs.common.utils.ListUtils;
import com.wisdom.iwcs.commonDto.fliterCondition.stockCondition.OutstockCalConditon;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDataDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.stock.StockSnMapper;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskDetailOutstockProcessMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskOutstockStockMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.service.base.baseImpl.CommonService;
import com.wisdom.service.callHik.IGetOutPodService;
import com.wisdom.service.common.IStockCal;
import com.wisdom.service.outstock.IOutStockSupplementCallOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryNameConstants.*;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.PRE_SN;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.OUTSTOCK_TASK;

/**
 * 出库补充呼叫
 *
 * @author cecilia.yang
 * @version 2019/02/25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutStockSupplementCallOutService implements IOutStockSupplementCallOutService {
    private final Logger logger = LoggerFactory.getLogger(OutStockSupplementCallOutService.class);
    @Autowired
    private AgvTaskOutstockStockMapper agvTaskOutstockStockMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private IGetOutPodService IGetOutPodService;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private IStockCal iStockCal;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private StockSnMapper stockSnMapper;


    @Override
    @Async("PodTaskFinishedExecutor")
    @EventListener
    public void podTaskFinishedEventListener(PodTaskFinishedEvent podTaskFinishedEvent) {
        logger.info("PodTaskFinishedExecutor 接收到自定义事件");
        PodTaskFinishedEventInfos source = (PodTaskFinishedEventInfos) podTaskFinishedEvent.getSource();
        logger.info("PodTaskFinishedExecutor 接收到自定义事件,货架号：｛｝", source.getPodCode());
        supplementCallOut(source.getPodCode());
        logger.info("PodTaskFinishedExecutor 处理完成自定义事件,货架号:{}", source.getPodCode());

    }

    /**
     * 补充呼叫货架
     *
     * @param podCode
     */
    @Override
    public void supplementCallOut(String podCode) {
        List<AgvTaskOutstockStock> missingStockInfoList = agvTaskOutstockStockMapper.selectMissingOutstockStockInfoOrderByCreatedTimeAsc();
        if (missingStockInfoList.size() == 0) {
            logger.info("OutStockSupplementCallOutService,未发现需要补充的出库库存");
            return;
        }
        Dictionary dictionary = dictionaryMapper.selectByDictName(OUTSTOCK_CURRENT_SUPPLY_CHOICE);
        String currentSupplyChoice = dictionary.getDictValue();
        switch (currentSupplyChoice) {
            case OUTSTOCK_TOTAL_SUPPLY_CHOICE_A:
                DirectCalculateFreePod(podCode, missingStockInfoList);
                break;
            case OUTSTOCK_TOTAL_SUPPLY_CHOICE_B:
                reGlobalCalculateOutStockMissingPod(missingStockInfoList);
                break;
            default:
                logger.error("系统未定义的方案,{}", currentSupplyChoice);
        }
    }

    /**
     * 重新全局计算，并呼叫
     *
     * @param missingStockInfoList
     */
    private void reGlobalCalculateOutStockMissingPod(List<AgvTaskOutstockStock> missingStockInfoList) {
        OutstockCalConditon outstockCalConditon = new OutstockCalConditon();
        List<OutstockCalPodParamDTO> calParamList = new ArrayList<>();
        missingStockInfoList.stream().forEach(missingInfo -> {
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
            outstockCalPodParamDTO.setContainerCode(missingInfo.getContainerCode());
            outstockCalPodParamDTO.setSpecCode(missingInfo.getSpecCode());
            outstockCalPodParamDTO.setOutQty(missingInfo.getMissingQty());
            outstockCalPodParamDTO.setWbTaskNo(missingInfo.getTaskNo());
            outstockCalPodParamDTO.setHavePreSn(false);
            if (isMissingSn(missingInfo)) {
                List<String> missingSns = ListUtils.convertStringToList(missingInfo.getMissingSns());
                outstockCalPodParamDTO.setHavePreSn(true);
                outstockCalPodParamDTO.setPointSns(missingSns);
            }
            calParamList.add(outstockCalPodParamDTO);
        });
        outstockCalConditon.setOutstockCalPodParamDTOS(calParamList);
        List<OutstockCalPodResultDTO> resultList = iStockCal.calStockByStockCondition(outstockCalConditon);
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setTaskType(OUTSTOCK_TASK.getType());

        resultList.stream().forEach(result -> {
            if (result.getData().size() != 0) {
                WbAgvTask wbAgvTask = wbAgvTaskMapper.selectedByTaskNo(result.getRequestInfo().getWbTaskNo());
                getOutPodDTO.setWbCode(wbAgvTask.getWbCode());
                List<String> callBincodes = result.getData().stream().map(OutstockCalPodResultDataDTO::getBincode).collect(Collectors.toList());
                List<String> distinctByPodBincodes = commonService.distinctBinCodeByPodCode(callBincodes);
                List<OutstockCalPodResultDTO> taskProcess = new ArrayList<>();
                taskProcess.add(result);
                getOutPodDTO.setBincodes(distinctByPodBincodes);
                getOutPodDTO.setTaskProcess(taskProcess);
                IGetOutPodService.getOutPod(getOutPodDTO);
            }
        });
    }

    private boolean isMissingSn(AgvTaskOutstockStock missingInfo) {
        return !Strings.isNullOrEmpty(missingInfo.getMissingSns());
    }

    /**
     * 直接计算释放货架，并呼叫
     *
     * @param podCode
     * @param missingStockInfoList
     */
    private void DirectCalculateFreePod(String podCode, List<AgvTaskOutstockStock> missingStockInfoList) {
        List<Stock> stockList = stockMapper.selectExistAvailableQtyStockByPodCodeAndDeleteFlag(podCode, NOT_DELETED.getStatus());
        if (stockList.size() == 0) {
            logger.info("OutStockSupplementCallOutService，货架号:{},未发现有效库存", podCode);
            return;
        }
        boolean needSupplyPod = false;
        List<OutstockCalPodResultDTO> outstockProcessInfo = new ArrayList<>();
        List<String> totalCallBincodes = new ArrayList<>();
        String supplyTaskNo = "";
        for (AgvTaskOutstockStock missingStockInfo : missingStockInfoList) {
            //数量判断
            List<Stock> sameStockInfos = stockList.stream().filter(stock -> isSameStock(missingStockInfo, stock)).collect(Collectors.toList());
            if (sameStockInfos.size() != 0) {
                needSupplyPod = true;
                supplyTaskNo = missingStockInfo.getTaskNo();
                OutstockCalPodResultDTO outstockCalPodResultDTO = new OutstockCalPodResultDTO();
                OutstockCalPodParamDTO outstockCalPodParamDTO = new OutstockCalPodParamDTO();
                outstockCalPodParamDTO.setStgAreaCode(missingStockInfo.getStgAreaCode());
                outstockCalPodParamDTO.setCargoOwner(missingStockInfo.getCargoOwner());
                outstockCalPodParamDTO.setMatCode(missingStockInfo.getMatCode());
                outstockCalPodParamDTO.setBatchNum(missingStockInfo.getBatchNum());
                outstockCalPodParamDTO.setStkCharacter1(missingStockInfo.getStkCharacter1());
                outstockCalPodParamDTO.setStkCharacter2(missingStockInfo.getStkCharacter2());
                outstockCalPodParamDTO.setStkCharacter3(missingStockInfo.getStkCharacter3());
                outstockCalPodParamDTO.setStkCharacter4(missingStockInfo.getStkCharacter4());
                outstockCalPodParamDTO.setStkCharacter5(missingStockInfo.getStkCharacter5());
                outstockCalPodParamDTO.setOutQty(missingStockInfo.getMissingQty());
                outstockCalPodParamDTO.setContainerCode(missingStockInfo.getContainerCode());
                outstockCalPodParamDTO.setSpecCode(missingStockInfo.getSpecCode());
                outstockCalPodParamDTO.setWbTaskNo(missingStockInfo.getTaskNo());
                outstockCalPodParamDTO.setHavePreSn(false);
                List<String> missSns = new ArrayList<>();
                if (missingStockInfo.getPreSnFlag().equals(PRE_SN) && !Strings.isNullOrEmpty(missingStockInfo.getMissingSns())) {
                    outstockCalPodParamDTO.setHavePreSn(true);
                    missSns = ListUtils.convertStringToList(missingStockInfo.getMissingSns());
                    outstockCalPodParamDTO.setPointSns(missSns);
                }
                outstockCalPodResultDTO.setRequestInfo(outstockCalPodParamDTO);
                outstockCalPodResultDTO.setHavePreSn(false);
                List<String> totalCalSns = new ArrayList<>();
                List<String> totalMissingSns = new ArrayList<>();
                BigDecimal totalCalculateQty = new BigDecimal(0);
                List<OutstockCalPodResultDataDTO> calBincodeDataList = new ArrayList<>();
                for (Stock sameStock : sameStockInfos) {
                    totalCallBincodes.add(sameStock.getBincode());
                    OutstockCalPodResultDataDTO outstockCalPodResultDataDTO = new OutstockCalPodResultDataDTO();
                    outstockCalPodResultDataDTO.setBincode(sameStock.getBincode());
                    outstockCalPodResultDataDTO.setStkCode(sameStock.getStkCode());
                    List<StockSn> stockSnInfos = stockSnMapper.selectByStkCode(sameStock.getStkCode());
                    List<String> stockSns = stockSnInfos.stream().map(StockSn::getSn).collect(Collectors.toList());
                    stockSns.retainAll(missSns);
                    BigDecimal stockQty = sameStock.getAvailableQty().subtract(new BigDecimal(stockSns.size()));
                    BigDecimal missingQty = missingStockInfo.getMissingQty();
                    if (stockQty.compareTo(missingQty) == -1) {
                        outstockCalPodResultDataDTO.setOutstockQty(stockQty);
                        totalCalculateQty = totalCalculateQty.add(stockQty);
                    } else {
                        outstockCalPodResultDataDTO.setOutstockQty(missingQty);
                        totalCalculateQty = totalCalculateQty.add(missingQty);
                    }
                    if (stockSns.size() != 0) {
                        totalCalSns.addAll(stockSns);
                        missSns.removeAll(stockSns);
                        totalMissingSns.addAll(missSns);
                        outstockCalPodResultDataDTO.setOutSns(stockSns);
                    }
                    calBincodeDataList.add(outstockCalPodResultDataDTO);
                }
                outstockCalPodResultDTO.setData(calBincodeDataList);
                outstockCalPodResultDTO.setTotalCalculateQty(totalCalculateQty);
                if (totalCalSns.size() != 0 || totalMissingSns.size() != 0) {
                    outstockCalPodResultDTO.setHavePreSn(true);
                    outstockCalPodResultDTO.setTotalCalculateSns(totalCalSns);
                    outstockCalPodResultDTO.setMissingSns(totalMissingSns);
                }
                outstockProcessInfo.add(outstockCalPodResultDTO);
                break;
            }
        }
        if (needSupplyPod) {
            List<String> distinctByPodBincodes = commonService.distinctBinCodeByPodCode(totalCallBincodes);
            GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
            getOutPodDTO.setBincodes(distinctByPodBincodes);
            WbAgvTask wbAgvTask = wbAgvTaskMapper.selectedByTaskNo(supplyTaskNo);
            logger.info("OutStockSupplementCallOutService,货架号:{}被选中补充库存,补充点位:{}", podCode, wbAgvTask.getWbCode());
            getOutPodDTO.setWbCode(wbAgvTask.getWbCode());
            getOutPodDTO.setTaskType(OUTSTOCK_TASK.getType());
            getOutPodDTO.setTaskProcess(outstockProcessInfo);
            IGetOutPodService.getOutPod(getOutPodDTO);
        }
    }

    private boolean isSameStock(AgvTaskOutstockStock missingStockInfo, Stock stock) {
        return stock.getStgAreaCode().equals(missingStockInfo.getStgAreaCode()) && stock.getMatCode().equals(missingStockInfo.getMatCode()) && stock.getStgAreaCode().equals(missingStockInfo.getStgAreaCode()) && stock.getCargoOwner().equals(missingStockInfo.getCargoOwner()) && stock.getBatchNum().equals(missingStockInfo.getBatchNum()) && stock.getStkCharacter1().equals(missingStockInfo.getStkCharacter1()) && stock.getStkCharacter2().equals(missingStockInfo.getStkCharacter2()) && stock.getStkCharacter3().equals(missingStockInfo.getStkCharacter3()) && stock.getStkCharacter4().equals(missingStockInfo.getStkCharacter4()) && stock.getStkCharacter5().equals(missingStockInfo.getStkCharacter5()) && stock.getContainerCode().equals(missingStockInfo.getContainerCode()) && stock.getSpecCode().equals(missingStockInfo.getSpecCode()) && (stock.getAvailableQty().compareTo(missingStockInfo.getMissingQty()) == 0 || stock.getAvailableQty().compareTo(missingStockInfo.getMissingQty()) == 1);
    }
}

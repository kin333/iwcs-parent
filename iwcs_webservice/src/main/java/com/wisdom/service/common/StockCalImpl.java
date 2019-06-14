package com.wisdom.service.common;

import com.wisdom.annotation.LogExecuteParamsAndTimeRecord;
import com.wisdom.annotation.LogExecuteTimeRecord;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.commonDto.fliterCondition.stockCondition.OutstockCalConditon;
import com.wisdom.iwcs.commonDto.strategy.PodHitOutStockDto;
import com.wisdom.iwcs.commonDto.strategy.PodHitOutStockDtoScoreDescComparator;
import com.wisdom.iwcs.commonDto.strategy.StockQtyAscComparator;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDataDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.iwcs.mapper.stock.StockSnMapper;
import com.wisdom.service.base.IBaseBizConCurrentRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 库存计算
 *
 * @author ted
 * @create 2019-03-04 下午3:05
 **/
@Service
@LogExecuteParamsAndTimeRecord
public class StockCalImpl implements IStockCal {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockSnMapper stockSnMapper;
    @Autowired
    private IBaseBizConCurrentRulesService IBaseBizConCurrentRulesService;

    @Override
    public List<OutstockCalPodResultDTO> calStockByStockCondition(OutstockCalConditon outstockCalConditon) {

        //校验判断指定SN不可与按个数呼叫混用

        OutstockCalPodResultDTO outstockCalPodResult = new OutstockCalPodResultDTO();
        List<OutstockCalPodParamDTO> outstockCalPodParamDTOS = outstockCalConditon.getOutstockCalPodParamDTOS();
        long specSnCount = outstockCalPodParamDTOS.stream().filter(pp -> pp.getPointSns() != null && !pp.getPointSns().isEmpty() && pp.getPointSns().size() > 0).count();
        if (specSnCount != 0 && specSnCount != outstockCalPodParamDTOS.size()) {
            throw new BusinessException("指定sn出库与按个数出库不可同时进行，请分开出库");
        }
        boolean isSpecSnOut = false;
        if (specSnCount > 0) {
            isSpecSnOut = true;
        }


        //获取不可并发业务类型参数,用于排除因业务冲突不可呼叫的货架
        Integer unableConcurrentBizTypeValuesSum = IBaseBizConCurrentRulesService.returnUnableConcurrentBizTypeValuesSum(PodTaskLockEnum.OUTSTOCK_TASK);


        List<Stock> allHitReqStocks = new ArrayList<Stock>();
        for (OutstockCalPodParamDTO outstockCalPodParamDTO : outstockCalPodParamDTOS) {
            outstockCalPodParamDTO.setExcludeLock(unableConcurrentBizTypeValuesSum);
            List<Stock> stocks = stockMapper.selectAvailableStockByStockCharacter(outstockCalPodParamDTO);
            allHitReqStocks.addAll(stocks);
        }
        //排除掉指定货架
        List<String> excludePods = outstockCalConditon.getExcludePods();
        allHitReqStocks = allHitReqStocks.stream().filter(s -> !excludePods.contains(s.getPodCode())).collect(Collectors.toList());


        //每个货架上满足出库的库存情况
        Map<String, List<Stock>> podStockMap = allHitReqStocks.stream().collect(Collectors.groupingBy(Stock::getPodCode));
        //转化为货架－库存结构
        List<PodHitOutStockDto> podHitOutStockDtos = new ArrayList<>();
        for (Map.Entry<String, List<Stock>> entry : podStockMap.entrySet()) {
            String podcode = entry.getKey();
            List<Stock> stocksOnPod = entry.getValue();
            PodHitOutStockDto podHitOutStockDto = new PodHitOutStockDto(podcode, stocksOnPod);
            podHitOutStockDto.calHitScore(outstockCalPodParamDTOS);
            podHitOutStockDtos.add(podHitOutStockDto);
        }
        //满足度排序
        Collections.sort(podHitOutStockDtos, new PodHitOutStockDtoScoreDescComparator());
        List<OutstockCalPodResultDTO> outstockCalPodResultDTOS = new ArrayList<>();
        //计算满足需求货架
        if (isSpecSnOut) {
            outstockCalPodResultDTOS = calOutstockCallBySnPodResult(outstockCalPodParamDTOS, podHitOutStockDtos);
        } else {
            outstockCalPodResultDTOS = calOutstockCallByCountPodResult(outstockCalPodParamDTOS, podHitOutStockDtos);
        }


        return outstockCalPodResultDTOS;
    }

    private List<OutstockCalPodResultDTO> calOutstockCallBySnPodResult(List<OutstockCalPodParamDTO> outstockCalPodParamDTOS, List<PodHitOutStockDto> podHitOutStockDtos) {
        List<Stock> stockList = podHitOutStockDtos.stream().flatMap(p -> p.getPodHitStocks().stream()).collect(Collectors.toList());
        List<String> stkCodes = stockList.stream().map(s -> s.getStkCode()).collect(Collectors.toList());
        List<StockSn> stockSns = stockSnMapper.selectByStkCodeList(stkCodes);
        List<OutstockCalPodResultDTO> outstockCalPodResultDTOS = new ArrayList<>();
        outstockCalPodParamDTOS.stream().forEach(outParam -> {
            List<String> pointSns = outParam.getPointSns();
            OutstockCalPodResultDTO outstockCalPodResultDTO = new OutstockCalPodResultDTO();
            ArrayList<OutstockCalPodResultDataDTO> outstockCalPodResultDataDTOS = new ArrayList<>();
            outstockCalPodResultDTO.setRequestInfo(outParam);
            outstockCalPodResultDTO.setTotalCalculateQty(new BigDecimal(0));
            outstockCalPodResultDTO.setRequestInfo(outParam);
            outstockCalPodResultDTO.setData(outstockCalPodResultDataDTOS);

            List<StockSn> hitRequestStockSnList = stockSns.stream().filter(stocksn -> pointSns.contains(stocksn)).collect(Collectors.toList());
            List<String> hitRequestSns = hitRequestStockSnList.stream().map(s -> s.getSn()).collect(Collectors.toList());
            outstockCalPodResultDTO.setTotalCalculateSns(hitRequestSns);

            Map<String, List<StockSn>> stkCode2StockSnMap = hitRequestStockSnList.stream().collect(Collectors.groupingBy(StockSn::getStkCode));
            for (String stkCode : stkCode2StockSnMap.keySet()
            ) {
                List<StockSn> stockSnsList = stkCode2StockSnMap.get(stkCode);
                Optional<Stock> stockOpt = stockList.stream().filter(s -> s.getStkCode().equals(stockSns.get(0).getStkCode())).findFirst();
                if (stockOpt.isPresent()) {
                    OutstockCalPodResultDataDTO outstockCalPodResultDataDTO = new OutstockCalPodResultDataDTO();
                    outstockCalPodResultDataDTO.setBincode(stockOpt.get().getBincode());
                    outstockCalPodResultDataDTO.setStkCode(stkCode);
                    outstockCalPodResultDataDTO.setOutSns(stockSnsList.stream().map(s -> s.getSn()).collect(Collectors.toList()));
                    outstockCalPodResultDataDTOS.add(outstockCalPodResultDataDTO);
                } else {
                    continue;
                }


            }
            outstockCalPodResultDTOS.add(outstockCalPodResultDTO);

        });
        return outstockCalPodResultDTOS;
    }

    /**
     * 筛选出符合条件的最优库存
     *
     * @param outstockCalPodParamDTOS
     * @param podHitOutStockDtos
     * @return
     */
    @LogExecuteTimeRecord
    private List<OutstockCalPodResultDTO> calOutstockCallByCountPodResult(List<OutstockCalPodParamDTO> outstockCalPodParamDTOS, List<PodHitOutStockDto> podHitOutStockDtos) {

        Map<OutstockCalPodParamDTO, OutstockCalPodResultDTO> outstockCalPodParamDTOOutstockCalPodResultDTOHashMap = new HashMap<OutstockCalPodParamDTO, OutstockCalPodResultDTO>();
        podHitOutStockDtos.stream().forEach(podHitOutStockDto -> {
            outstockCalPodParamDTOS.stream().forEach(outParam -> {
                OutstockCalPodResultDTO outstockCalPodResultDTO = outstockCalPodParamDTOOutstockCalPodResultDTOHashMap.get(outParam);
                if (outstockCalPodResultDTO == null) {
                    outstockCalPodResultDTO = new OutstockCalPodResultDTO();
                    outstockCalPodResultDTO.setRequestInfo(outParam);
                    outstockCalPodResultDTO.setTotalCalculateQty(new BigDecimal(0));
                    outstockCalPodResultDTO.setRequestInfo(outParam);
                    outstockCalPodResultDTO.setData(new ArrayList<OutstockCalPodResultDataDTO>());
                    outstockCalPodParamDTOOutstockCalPodResultDTOHashMap.put(outParam, outstockCalPodResultDTO);
                }
                List<OutstockCalPodResultDataDTO> data = outstockCalPodResultDTO.getData();
                List<Stock> hitStockForOutstockCalPodParam = podHitOutStockDto.getHitStockForOutstockCalPodParam(outParam);
                //需出库数
                BigDecimal outQty = outstockCalPodResultDTO.getRequestInfo().getOutQty();
                //缺失数
                BigDecimal missingQty = outstockCalPodResultDTO.getMissingQty();
                //已呼叫数
                BigDecimal totalCalculatedQty = outstockCalPodResultDTO.getTotalCalculateQty();
                if (missingQty.compareTo(new BigDecimal(0)) > 0) {
                    Collections.sort(hitStockForOutstockCalPodParam, new StockQtyAscComparator());
                    for (Stock hitstock : hitStockForOutstockCalPodParam) {
                        if (missingQty.compareTo(new BigDecimal(0)) < 1) {
                            break;
                        }
                        OutstockCalPodResultDataDTO outstockCalPodResultDataDTO = new OutstockCalPodResultDataDTO();
                        outstockCalPodResultDataDTO.setBincode(hitstock.getBincode());
                        BigDecimal stockAvailableQty = hitstock.getAvailableQty();
                        //
                        outstockCalPodResultDataDTO.setStkCode(hitstock.getStkCode());

                        if (missingQty.compareTo(stockAvailableQty) >= 0) {
                            outstockCalPodResultDataDTO.setOutstockQty(hitstock.getAvailableQty());
                            BigDecimal addedTotalTmp = totalCalculatedQty.add(hitstock.getAvailableQty());
                            totalCalculatedQty = addedTotalTmp;
                        } else {
                            outstockCalPodResultDataDTO.setOutstockQty(missingQty);
                            totalCalculatedQty = outQty;
                        }
                        outstockCalPodResultDTO.setTotalCalculateQty(totalCalculatedQty);
                        data.add(outstockCalPodResultDataDTO);

                    }

                }

            });
        });
        return new ArrayList<OutstockCalPodResultDTO>(outstockCalPodParamDTOOutstockCalPodResultDTOHashMap.values());

    }
}

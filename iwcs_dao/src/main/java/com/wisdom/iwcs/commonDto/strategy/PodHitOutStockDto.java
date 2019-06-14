package com.wisdom.iwcs.commonDto.strategy;

import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 货架满足库存
 *
 * @author ted
 * @create 2019-03-04 下午5:03
 **/
public class PodHitOutStockDto {

    private String podCode;
    private List<Stock> podHitStocks;
    private BigDecimal hitScore;


    public PodHitOutStockDto(String podCode, List<Stock> podHitStocks) {
        this.podCode = podCode;
        this.podHitStocks = podHitStocks;
    }


    public BigDecimal getHitScore() {
        return hitScore;
    }

    /**
     * 根据需求计算库存命中率
     *
     * @param calPodParams
     */
    public void calHitScore(List<OutstockCalPodParamDTO> calPodParams) {
        calPodParams.stream().forEach(calPodParamDTO -> {
            BigDecimal outQty = calPodParamDTO.getOutQty();
            List<Stock> hitStocksForParam = getHitStockForOutstockCalPodParam(calPodParamDTO);
            hitStocksForParam.stream().forEach(hsfp -> {
                BigDecimal availableQty = hsfp.getAvailableQty();
                BigDecimal fitPercent = availableQty.divide(outQty, 3, BigDecimal.ROUND_HALF_UP);
                BigDecimal one = new BigDecimal("1");
                int i = fitPercent.compareTo(one);
                if (i > 1) {
                    fitPercent = one;
                }
                if (this.hitScore == null) {
                    this.hitScore = new BigDecimal(0);
                }
                this.hitScore.add(fitPercent.multiply(new BigDecimal(100).multiply(fitPercent)));
            });

        });

    }

    public List<Stock> getHitStockForOutstockCalPodParam(OutstockCalPodParamDTO outstockCalPodParamDTO) {
        List<Stock> hitStocksForParam = this.podHitStocks.stream().filter(stock -> stockFitCalPodParams(outstockCalPodParamDTO, stock)).collect(Collectors.toList());
        return hitStocksForParam;

    }

    public boolean stockFitCalPodParams(OutstockCalPodParamDTO calPodParamDTO, Stock stock) {
        boolean match = true;
        if (calPodParamDTO == null || stock == null) {
            return false;
        } else {
            if (StringUtils.isNotBlank(calPodParamDTO.getStgAreaCode()) && !calPodParamDTO.getStgAreaCode().equals(stock.getStgAreaCode())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getBatchNum()) && !calPodParamDTO.getBatchNum().equals(stock.getBatchNum())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getCargoOwner()) && !calPodParamDTO.getCargoOwner().equals(stock.getCargoOwner())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getMatCode()) && !calPodParamDTO.getMatCode().equals(stock.getMatCode())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getStkCharacter1()) && !calPodParamDTO.getStkCharacter1().equals(stock.getStkCharacter1())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getStkCharacter2()) && !calPodParamDTO.getStkCharacter2().equals(stock.getStkCharacter2())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getStkCharacter3()) && !calPodParamDTO.getStkCharacter3().equals(stock.getStkCharacter3())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getStkCharacter4()) && !calPodParamDTO.getStkCharacter4().equals(stock.getStkCharacter4())) {
                match = false;
            }
            if (StringUtils.isNotBlank(calPodParamDTO.getStkCharacter5()) && !calPodParamDTO.getStkCharacter5().equals(stock.getStkCharacter5())) {
                match = false;
            }

            return match;

        }
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public List<Stock> getPodHitStocks() {
        return podHitStocks;
    }

    public void setPodHitStocks(List<Stock> podHitStocks) {
        this.podHitStocks = podHitStocks;
    }

    public void setHitScore(BigDecimal hitScore) {
        this.hitScore = hitScore;
    }
}

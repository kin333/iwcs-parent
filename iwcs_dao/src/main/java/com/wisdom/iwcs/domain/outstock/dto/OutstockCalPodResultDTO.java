package com.wisdom.iwcs.domain.outstock.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 返回出库计算的结果货架
 *
 * @Author: cecilia.yang
 * @Date: 2019/3/1 15:35
 */
public class OutstockCalPodResultDTO {

    private OutstockCalPodParamDTO requestInfo;
    /**
     * 已计算的总数量
     */
    private BigDecimal totalCalculateQty;
    /**
     * 缺失数量
     */
    private BigDecimal missingQty;
    /**
     * 是否指定SN出库
     */
    private boolean havePreSn;

    /**
     * 已计算的SN
     */
    private List<String> totalCalculateSns;

    /**
     * 缺失Sn
     */
    private List<String> missingSns;

    /**
     * 计算的bincode详情
     */
    private List<OutstockCalPodResultDataDTO> data;

    @Override
    public String toString() {
        return "OutstockCalPodResultDTO{" +
                "requestInfo=" + requestInfo +
                ", totalCalculateQty=" + totalCalculateQty +
                ", missingQty=" + missingQty +
                ", havePreSn=" + havePreSn +
                ", totalCalculateSns=" + totalCalculateSns +
                ", missingSns=" + missingSns +
                ", data=" + data +
                '}';
    }

    public boolean isHavePreSn() {
        return havePreSn;
    }

    public void setHavePreSn(boolean havePreSn) {
        this.havePreSn = havePreSn;
    }

    public List<String> getTotalCalculateSns() {
        return totalCalculateSns;
    }

    public void setTotalCalculateSns(List<String> totalCalculateSns) {
        this.totalCalculateSns = totalCalculateSns;
    }

    public List<String> getMissingSns() {
        return missingSns;
    }

    public void setMissingSns(List<String> missingSns) {
        this.missingSns = missingSns;
    }

    public OutstockCalPodParamDTO getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(OutstockCalPodParamDTO requestInfo) {
        this.requestInfo = requestInfo;
    }

    public BigDecimal getTotalCalculateQty() {
        return totalCalculateQty;
    }

    public void setTotalCalculateQty(BigDecimal totalCalculateQty) {
        this.totalCalculateQty = totalCalculateQty;
    }

    public BigDecimal getMissingQty() {
        if (requestInfo == null) {
            return new BigDecimal(0);
        } else {
            return requestInfo.getOutQty().subtract(totalCalculateQty == null ? new BigDecimal(0) : totalCalculateQty);
        }
    }


    public List<OutstockCalPodResultDataDTO> getData() {
        return data;
    }

    public void setData(List<OutstockCalPodResultDataDTO> data) {
        this.data = data;
    }
}

package com.wisdom.iwcs.commonDto.podPutaway;

import java.util.List;

/**
 * 上架方案
 *
 * @author ted
 * @create 2019-03-01 上午11:31
 **/
public class PodPutawayPlan {

    private String podCode;
    /**
     * bincode code
     */
    private List<String> bincodes;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public List<String> getBincodes() {
        return bincodes;
    }

    public void setBincodes(List<String> bincodes) {
        this.bincodes = bincodes;
    }
}

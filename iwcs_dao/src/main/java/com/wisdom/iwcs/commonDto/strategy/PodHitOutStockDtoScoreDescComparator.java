package com.wisdom.iwcs.commonDto.strategy;

import java.util.Comparator;

/**
 * 比较器，分值高的在前
 *
 * @author ted
 * @create 2019-03-05 上午9:17
 **/
public class PodHitOutStockDtoScoreDescComparator implements Comparator<PodHitOutStockDto> {

    @Override
    public int compare(PodHitOutStockDto podHitOutStockDto, PodHitOutStockDto t1) {

        return podHitOutStockDto.getHitScore().compareTo(t1.getHitScore());
    }
}

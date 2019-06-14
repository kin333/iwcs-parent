package com.wisdom.iwcs.commonDto.strategy;

import com.wisdom.iwcs.domain.stock.Stock;

import java.util.Comparator;

/**
 * 库存按照库存量升序排列
 *
 * @author ted
 * @create 2019-03-05 上午11:24
 **/
public class StockQtyAscComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock stock, Stock t1) {
        return -stock.getAvailableQty().compareTo(t1.getAvailableQty());
    }
}

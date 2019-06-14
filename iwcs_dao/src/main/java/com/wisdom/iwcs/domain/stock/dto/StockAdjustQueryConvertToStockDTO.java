package com.wisdom.iwcs.domain.stock.dto;

import com.wisdom.iwcs.domain.stock.Stock;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/22 10:22
 */
public class StockAdjustQueryConvertToStockDTO {
    private boolean haveNewStock;

    private Stock stock;

    @Override
    public String toString() {
        return "StockAdjustQueryConvertToStockDTO{" +
                "haveNewStock=" + haveNewStock +
                ", stock=" + stock +
                '}';
    }

    public boolean isHaveNewStock() {
        return haveNewStock;
    }

    public void setHaveNewStock(boolean haveNewStock) {
        this.haveNewStock = haveNewStock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}

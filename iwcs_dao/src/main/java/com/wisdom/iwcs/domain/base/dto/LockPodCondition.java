package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

/**
 * 锁定货架条件类
 * @author han
 */
public class LockPodCondition extends BaseLockCondition implements Cloneable{

    /**
     * 货架是否有货
     */
    @Column(name = "in_stock")
    public String inStock;

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}

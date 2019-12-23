package com.wisdom.iwcs.domain.base.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 锁定货架条件类
 * @author han
 */
@Getter
@Setter
public class LockPodCondition extends BaseLockCondition implements Cloneable{

    /**
     * 货架是否有货
     */
    @Column(name = "in_stock")
    public String inStock;
    /**
     * 货架类型
     */
    public String podType;

}

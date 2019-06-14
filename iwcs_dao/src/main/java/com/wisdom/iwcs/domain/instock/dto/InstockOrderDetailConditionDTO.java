package com.wisdom.iwcs.domain.instock.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class InstockOrderDetailConditionDTO {

    /**
     * 业务单据编号（冗余）
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 子单编号
     */
    @Column(name = "sub_order_no")
    private String subOrderNo;

    /**
     * 物料编号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 工厂编号
     */
    @Column(name = "plant_code")
    private String plantCode;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 批次号
     */
    @Column(name = "batch_num")
    private String batchNum;

    /**
     * 特殊库存标识
     */
    @Column(name = "spe_stk_flag")
    private String speStkFlag;

    /**
     * 特殊库存编号
     */
    @Column(name = "spe_stk_num")
    private String speStkNum;

    /**
     * 预计入库开始时间
     */
    private Date startExpectedDate;

    /**
     * 预计入库结束时间
     */
    private Date endExpectedDate;

    /**
     * 物料数量
     */
    @Column(name = "mat_qty")
    private BigDecimal matQty;

    /**
     * 物料单位
     */
    @Column(name = "mat_unit")
    private String matUnit;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 预计创建开始时间
     */
    private Date startCreateTime;

    /**
     * 预计创建结束时间
     */
    private Date endCreateTime;

    /**
     * 已入库数量
     */
    @Column(name = "in_storage_num")
    private BigDecimal inStorageNum;

    /**
     * 单据明细状态,如0：已创建登
     */
    @Column(name = "order_detail_status")
    private String orderDetailStatus;
}

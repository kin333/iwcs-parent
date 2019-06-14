package com.wisdom.iwcs.domain.instock.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class InstockRecordConditionDto {

    /**
     * 入库业务类型：NORMAL_INSTOCK(正常入库)、异常库存调整（ABNORMAL_INSTOCK）等
     */
    @Column(name = "instk_biz_type")
    private String instkBizType;

    /**
     * 库存唯一编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 业务单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 子单号
     */
    @Column(name = "sub_order_no")
    private String subOrderNo;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 物料号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 入库方式，0按数量，1按条码
     */
    @Column(name = "instock_type")
    private String instockType;

    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 特征值1
     */
    @Column(name = "stk_character1")
    private String stkCharacter1;

    /**
     * 特征值2
     */
    @Column(name = "stk_character2")
    private String stkCharacter2;

    /**
     * 特征值3
     */
    @Column(name = "stk_character3")
    private String stkCharacter3;

    /**
     * 特征值4
     */
    @Column(name = "stk_character4")
    private String stkCharacter4;

    /**
     * 特征值5
     */
    @Column(name = "stk_character5")
    private String stkCharacter5;

    /**
     * 仓库编号
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 库区编号
     */
    @Column(name = "area_code")
    private String areaCode;

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
     * 按容器管理标识，0否，1是
     */
    @Column(name = "container_flag")
    private String containerFlag;


    /**
     * 库存冻结标识，0未冻结，1冻结
     */
    @Column(name = "freeze_flag")
    private String freezeFlag;

    /**
     * 冻结原因
     */
    @Column(name = "blk_rea_code")
    private String blkReaCode;

    /**
     * 工厂
     */
    @Column(name = "plant_code")
    private String plantCode;

    /**
     * 容器编号
     */
    @Column(name = "container_code")
    private String containerCode;

    /**
     * 预留字段
     */
    @Column(name = "record_prop1")
    private String recordProp1;

    /**
     * 预留字段
     */
    @Column(name = "record_prop2")
    private String recordProp2;

    /**
     * 预留字段
     */
    @Column(name = "record_prop3")
    private String recordProp3;


    /**
     * 创建开始时间
     */
    private Date startCreatedTime;

    /**
     * 创建结束时间
     */
    private Date createdEndTime;


    /**
     * 上游用户编码
     */
    @Column(name = "src_user_code")
    private String srcUserCode;

    /**
     * 货架号
     */
    private String podCode;


}

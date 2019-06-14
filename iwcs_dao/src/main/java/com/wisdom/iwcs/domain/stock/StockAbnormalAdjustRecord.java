package com.wisdom.iwcs.domain.stock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "stock_abnormal_adjust_record")
public class StockAbnormalAdjustRecord {
    @Id
    private Integer id;

    /**
     * 调整前库存编号
     */
    @Column(name = "before_adjust_stk_code")
    private String beforeAdjustStkCode;

    /**
     * 调整后库存编号
     */
    @Column(name = "after_adjust_stk_code")
    private String afterAdjustStkCode;

    /**
     * 上游调整编号
     */
    @Column(name = "src_adjust_no")
    private String srcAdjustNo;

    /**
     * 系统自生成调整编号,唯一编号
     */
    @Column(name = "sys_adjust_no")
    private String sysAdjustNo;

    /**
     * 调整类型
     */
    @Column(name = "adjust_type")
    private String adjustType;

    /**
     * 上游调整人
     */
    @Column(name = "src_adjust_user_code")
    private String srcAdjustUserCode;

    /**
     * 库区编号
     */
    @Column(name = "stg_area_code")
    private String stgAreaCode;

    /**
     * 调整数量
     */
    @Column(name = "adjust_qty")
    private BigDecimal adjustQty;

    /**
     * 调整前SN
     */
    @Column(name = "before_sno")
    private String beforeSno;

    /**
     * 调整后SN
     */
    @Column(name = "after_sno")
    private String afterSno;

    /**
     * 调整前货主
     */
    @Column(name = "before_cargo_owner")
    private String beforeCargoOwner;

    /**
     * 调整前物料号
     */
    @Column(name = "before_mat_code")
    private String beforeMatCode;

    /**
     * 调整前仓位号
     */
    @Column(name = "before_bincode")
    private String beforeBincode;

    /**
     * 调整前货架号
     */
    @Column(name = "before_pod_code")
    private String beforePodCode;

    /**
     * 调整前批次号
     */
    @Column(name = "before_batch_num")
    private String beforeBatchNum;

    /**
     * 调整前容器编号
     */
    @Column(name = "before_container_code")
    private String beforeContainerCode;

    /**
     * 调整前规格编号
     */
    @Column(name = "before_spec_code")
    private String beforeSpecCode;

    /**
     * 调整前特征值1
     */
    @Column(name = "before_stk_character1")
    private String beforeStkCharacter1;

    /**
     * 调整前特征值2
     */
    @Column(name = "before_stk_character2")
    private String beforeStkCharacter2;

    /**
     * 调整前特征值3
     */
    @Column(name = "before_stk_character3")
    private String beforeStkCharacter3;

    /**
     * 调整前特征值4
     */
    @Column(name = "before_stk_character4")
    private String beforeStkCharacter4;

    /**
     * 调整前特征值5
     */
    @Column(name = "before_stk_character5")
    private String beforeStkCharacter5;

    /**
     * 调整后货主
     */
    @Column(name = "after_cargo_owner")
    private String afterCargoOwner;

    /**
     * 调整后物料号
     */
    @Column(name = "after_mat_code")
    private String afterMatCode;

    /**
     * 调整后仓位号
     */
    @Column(name = "after_bincode")
    private String afterBincode;

    /**
     * 调整后货架号
     */
    @Column(name = "after_pod_code")
    private String afterPodCode;

    /**
     * 调整后批次号
     */
    @Column(name = "after_batch_num")
    private String afterBatchNum;

    /**
     * 调整后容器编号
     */
    @Column(name = "after_container_code")
    private String afterContainerCode;

    /**
     * 调整后规格编号
     */
    @Column(name = "after_spec_code")
    private String afterSpecCode;

    /**
     * 调整后特征值1
     */
    @Column(name = "after_stk_character1")
    private String afterStkCharacter1;

    /**
     * 调整后特征值2
     */
    @Column(name = "after_stk_character2")
    private String afterStkCharacter2;

    /**
     * 调整后特征值3
     */
    @Column(name = "after_stk_character3")
    private String afterStkCharacter3;

    /**
     * 调整后特征值4
     */
    @Column(name = "after_stk_character4")
    private String afterStkCharacter4;

    /**
     * 调整后特征值5
     */
    @Column(name = "after_stk_character5")
    private String afterStkCharacter5;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 调整地码编号
     */
    @Column(name = "adjust_wb_code")
    private String adjustWbCode;

    /**
     * 调整SN
     */
    @Column(name = "adjust_sn_list")
    private String adjustSnList;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取调整前库存编号
     *
     * @return before_adjust_stk_code - 调整前库存编号
     */
    public String getBeforeAdjustStkCode() {
        return beforeAdjustStkCode;
    }

    /**
     * 设置调整前库存编号
     *
     * @param beforeAdjustStkCode 调整前库存编号
     */
    public void setBeforeAdjustStkCode(String beforeAdjustStkCode) {
        this.beforeAdjustStkCode = beforeAdjustStkCode == null ? null : beforeAdjustStkCode.trim();
    }

    /**
     * 获取调整后库存编号
     *
     * @return after_adjust_stk_code - 调整后库存编号
     */
    public String getAfterAdjustStkCode() {
        return afterAdjustStkCode;
    }

    /**
     * 设置调整后库存编号
     *
     * @param afterAdjustStkCode 调整后库存编号
     */
    public void setAfterAdjustStkCode(String afterAdjustStkCode) {
        this.afterAdjustStkCode = afterAdjustStkCode == null ? null : afterAdjustStkCode.trim();
    }

    /**
     * 获取上游调整编号
     *
     * @return src_adjust_no - 上游调整编号
     */
    public String getSrcAdjustNo() {
        return srcAdjustNo;
    }

    /**
     * 设置上游调整编号
     *
     * @param srcAdjustNo 上游调整编号
     */
    public void setSrcAdjustNo(String srcAdjustNo) {
        this.srcAdjustNo = srcAdjustNo == null ? null : srcAdjustNo.trim();
    }

    /**
     * 获取系统自生成调整编号,唯一编号
     *
     * @return sys_adjust_no - 系统自生成调整编号,唯一编号
     */
    public String getSysAdjustNo() {
        return sysAdjustNo;
    }

    /**
     * 设置系统自生成调整编号,唯一编号
     *
     * @param sysAdjustNo 系统自生成调整编号,唯一编号
     */
    public void setSysAdjustNo(String sysAdjustNo) {
        this.sysAdjustNo = sysAdjustNo == null ? null : sysAdjustNo.trim();
    }

    /**
     * 获取调整类型
     *
     * @return adjust_type - 调整类型
     */
    public String getAdjustType() {
        return adjustType;
    }

    /**
     * 设置调整类型
     *
     * @param adjustType 调整类型
     */
    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType == null ? null : adjustType.trim();
    }

    /**
     * 获取上游调整人
     *
     * @return src_adjust_user_code - 上游调整人
     */
    public String getSrcAdjustUserCode() {
        return srcAdjustUserCode;
    }

    /**
     * 设置上游调整人
     *
     * @param srcAdjustUserCode 上游调整人
     */
    public void setSrcAdjustUserCode(String srcAdjustUserCode) {
        this.srcAdjustUserCode = srcAdjustUserCode == null ? null : srcAdjustUserCode.trim();
    }

    /**
     * 获取库区编号
     *
     * @return stg_area_code - 库区编号
     */
    public String getStgAreaCode() {
        return stgAreaCode;
    }

    /**
     * 设置库区编号
     *
     * @param stgAreaCode 库区编号
     */
    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode == null ? null : stgAreaCode.trim();
    }

    /**
     * 获取调整数量
     *
     * @return adjust_qty - 调整数量
     */
    public BigDecimal getAdjustQty() {
        return adjustQty;
    }

    /**
     * 设置调整数量
     *
     * @param adjustQty 调整数量
     */
    public void setAdjustQty(BigDecimal adjustQty) {
        this.adjustQty = adjustQty;
    }

    /**
     * 获取调整前SN
     *
     * @return before_sno - 调整前SN
     */
    public String getBeforeSno() {
        return beforeSno;
    }

    /**
     * 设置调整前SN
     *
     * @param beforeSno 调整前SN
     */
    public void setBeforeSno(String beforeSno) {
        this.beforeSno = beforeSno == null ? null : beforeSno.trim();
    }

    /**
     * 获取调整后SN
     *
     * @return after_sno - 调整后SN
     */
    public String getAfterSno() {
        return afterSno;
    }

    /**
     * 设置调整后SN
     *
     * @param afterSno 调整后SN
     */
    public void setAfterSno(String afterSno) {
        this.afterSno = afterSno == null ? null : afterSno.trim();
    }

    /**
     * 获取调整前货主
     *
     * @return before_cargo_owner - 调整前货主
     */
    public String getBeforeCargoOwner() {
        return beforeCargoOwner;
    }

    /**
     * 设置调整前货主
     *
     * @param beforeCargoOwner 调整前货主
     */
    public void setBeforeCargoOwner(String beforeCargoOwner) {
        this.beforeCargoOwner = beforeCargoOwner == null ? null : beforeCargoOwner.trim();
    }

    /**
     * 获取调整前物料号
     *
     * @return before_mat_code - 调整前物料号
     */
    public String getBeforeMatCode() {
        return beforeMatCode;
    }

    /**
     * 设置调整前物料号
     *
     * @param beforeMatCode 调整前物料号
     */
    public void setBeforeMatCode(String beforeMatCode) {
        this.beforeMatCode = beforeMatCode == null ? null : beforeMatCode.trim();
    }

    /**
     * 获取调整前仓位号
     *
     * @return before_bincode - 调整前仓位号
     */
    public String getBeforeBincode() {
        return beforeBincode;
    }

    /**
     * 设置调整前仓位号
     *
     * @param beforeBincode 调整前仓位号
     */
    public void setBeforeBincode(String beforeBincode) {
        this.beforeBincode = beforeBincode == null ? null : beforeBincode.trim();
    }

    /**
     * 获取调整前货架号
     *
     * @return before_pod_code - 调整前货架号
     */
    public String getBeforePodCode() {
        return beforePodCode;
    }

    /**
     * 设置调整前货架号
     *
     * @param beforePodCode 调整前货架号
     */
    public void setBeforePodCode(String beforePodCode) {
        this.beforePodCode = beforePodCode == null ? null : beforePodCode.trim();
    }

    /**
     * 获取调整前批次号
     *
     * @return before_batch_num - 调整前批次号
     */
    public String getBeforeBatchNum() {
        return beforeBatchNum;
    }

    /**
     * 设置调整前批次号
     *
     * @param beforeBatchNum 调整前批次号
     */
    public void setBeforeBatchNum(String beforeBatchNum) {
        this.beforeBatchNum = beforeBatchNum == null ? null : beforeBatchNum.trim();
    }

    /**
     * 获取调整前容器编号
     *
     * @return before_container_code - 调整前容器编号
     */
    public String getBeforeContainerCode() {
        return beforeContainerCode;
    }

    /**
     * 设置调整前容器编号
     *
     * @param beforeContainerCode 调整前容器编号
     */
    public void setBeforeContainerCode(String beforeContainerCode) {
        this.beforeContainerCode = beforeContainerCode == null ? null : beforeContainerCode.trim();
    }

    /**
     * 获取调整前规格编号
     *
     * @return before_spec_code - 调整前规格编号
     */
    public String getBeforeSpecCode() {
        return beforeSpecCode;
    }

    /**
     * 设置调整前规格编号
     *
     * @param beforeSpecCode 调整前规格编号
     */
    public void setBeforeSpecCode(String beforeSpecCode) {
        this.beforeSpecCode = beforeSpecCode == null ? null : beforeSpecCode.trim();
    }

    /**
     * 获取调整前特征值1
     *
     * @return before_stk_character1 - 调整前特征值1
     */
    public String getBeforeStkCharacter1() {
        return beforeStkCharacter1;
    }

    /**
     * 设置调整前特征值1
     *
     * @param beforeStkCharacter1 调整前特征值1
     */
    public void setBeforeStkCharacter1(String beforeStkCharacter1) {
        this.beforeStkCharacter1 = beforeStkCharacter1 == null ? null : beforeStkCharacter1.trim();
    }

    /**
     * 获取调整前特征值2
     *
     * @return before_stk_character2 - 调整前特征值2
     */
    public String getBeforeStkCharacter2() {
        return beforeStkCharacter2;
    }

    /**
     * 设置调整前特征值2
     *
     * @param beforeStkCharacter2 调整前特征值2
     */
    public void setBeforeStkCharacter2(String beforeStkCharacter2) {
        this.beforeStkCharacter2 = beforeStkCharacter2 == null ? null : beforeStkCharacter2.trim();
    }

    /**
     * 获取调整前特征值3
     *
     * @return before_stk_character3 - 调整前特征值3
     */
    public String getBeforeStkCharacter3() {
        return beforeStkCharacter3;
    }

    /**
     * 设置调整前特征值3
     *
     * @param beforeStkCharacter3 调整前特征值3
     */
    public void setBeforeStkCharacter3(String beforeStkCharacter3) {
        this.beforeStkCharacter3 = beforeStkCharacter3 == null ? null : beforeStkCharacter3.trim();
    }

    /**
     * 获取调整前特征值4
     *
     * @return before_stk_character4 - 调整前特征值4
     */
    public String getBeforeStkCharacter4() {
        return beforeStkCharacter4;
    }

    /**
     * 设置调整前特征值4
     *
     * @param beforeStkCharacter4 调整前特征值4
     */
    public void setBeforeStkCharacter4(String beforeStkCharacter4) {
        this.beforeStkCharacter4 = beforeStkCharacter4 == null ? null : beforeStkCharacter4.trim();
    }

    /**
     * 获取调整前特征值5
     *
     * @return before_stk_character5 - 调整前特征值5
     */
    public String getBeforeStkCharacter5() {
        return beforeStkCharacter5;
    }

    /**
     * 设置调整前特征值5
     *
     * @param beforeStkCharacter5 调整前特征值5
     */
    public void setBeforeStkCharacter5(String beforeStkCharacter5) {
        this.beforeStkCharacter5 = beforeStkCharacter5 == null ? null : beforeStkCharacter5.trim();
    }

    /**
     * 获取调整后货主
     *
     * @return after_cargo_owner - 调整后货主
     */
    public String getAfterCargoOwner() {
        return afterCargoOwner;
    }

    /**
     * 设置调整后货主
     *
     * @param afterCargoOwner 调整后货主
     */
    public void setAfterCargoOwner(String afterCargoOwner) {
        this.afterCargoOwner = afterCargoOwner == null ? null : afterCargoOwner.trim();
    }

    /**
     * 获取调整后物料号
     *
     * @return after_mat_code - 调整后物料号
     */
    public String getAfterMatCode() {
        return afterMatCode;
    }

    /**
     * 设置调整后物料号
     *
     * @param afterMatCode 调整后物料号
     */
    public void setAfterMatCode(String afterMatCode) {
        this.afterMatCode = afterMatCode == null ? null : afterMatCode.trim();
    }

    /**
     * 获取调整后仓位号
     *
     * @return after_bincode - 调整后仓位号
     */
    public String getAfterBincode() {
        return afterBincode;
    }

    /**
     * 设置调整后仓位号
     *
     * @param afterBincode 调整后仓位号
     */
    public void setAfterBincode(String afterBincode) {
        this.afterBincode = afterBincode == null ? null : afterBincode.trim();
    }

    /**
     * 获取调整后货架号
     *
     * @return after_pod_code - 调整后货架号
     */
    public String getAfterPodCode() {
        return afterPodCode;
    }

    /**
     * 设置调整后货架号
     *
     * @param afterPodCode 调整后货架号
     */
    public void setAfterPodCode(String afterPodCode) {
        this.afterPodCode = afterPodCode == null ? null : afterPodCode.trim();
    }

    /**
     * 获取调整后批次号
     *
     * @return after_batch_num - 调整后批次号
     */
    public String getAfterBatchNum() {
        return afterBatchNum;
    }

    /**
     * 设置调整后批次号
     *
     * @param afterBatchNum 调整后批次号
     */
    public void setAfterBatchNum(String afterBatchNum) {
        this.afterBatchNum = afterBatchNum == null ? null : afterBatchNum.trim();
    }

    /**
     * 获取调整后容器编号
     *
     * @return after_container_code - 调整后容器编号
     */
    public String getAfterContainerCode() {
        return afterContainerCode;
    }

    /**
     * 设置调整后容器编号
     *
     * @param afterContainerCode 调整后容器编号
     */
    public void setAfterContainerCode(String afterContainerCode) {
        this.afterContainerCode = afterContainerCode == null ? null : afterContainerCode.trim();
    }

    /**
     * 获取调整后规格编号
     *
     * @return after_spec_code - 调整后规格编号
     */
    public String getAfterSpecCode() {
        return afterSpecCode;
    }

    /**
     * 设置调整后规格编号
     *
     * @param afterSpecCode 调整后规格编号
     */
    public void setAfterSpecCode(String afterSpecCode) {
        this.afterSpecCode = afterSpecCode == null ? null : afterSpecCode.trim();
    }

    /**
     * 获取调整后特征值1
     *
     * @return after_stk_character1 - 调整后特征值1
     */
    public String getAfterStkCharacter1() {
        return afterStkCharacter1;
    }

    /**
     * 设置调整后特征值1
     *
     * @param afterStkCharacter1 调整后特征值1
     */
    public void setAfterStkCharacter1(String afterStkCharacter1) {
        this.afterStkCharacter1 = afterStkCharacter1 == null ? null : afterStkCharacter1.trim();
    }

    /**
     * 获取调整后特征值2
     *
     * @return after_stk_character2 - 调整后特征值2
     */
    public String getAfterStkCharacter2() {
        return afterStkCharacter2;
    }

    /**
     * 设置调整后特征值2
     *
     * @param afterStkCharacter2 调整后特征值2
     */
    public void setAfterStkCharacter2(String afterStkCharacter2) {
        this.afterStkCharacter2 = afterStkCharacter2 == null ? null : afterStkCharacter2.trim();
    }

    /**
     * 获取调整后特征值3
     *
     * @return after_stk_character3 - 调整后特征值3
     */
    public String getAfterStkCharacter3() {
        return afterStkCharacter3;
    }

    /**
     * 设置调整后特征值3
     *
     * @param afterStkCharacter3 调整后特征值3
     */
    public void setAfterStkCharacter3(String afterStkCharacter3) {
        this.afterStkCharacter3 = afterStkCharacter3 == null ? null : afterStkCharacter3.trim();
    }

    /**
     * 获取调整后特征值4
     *
     * @return after_stk_character4 - 调整后特征值4
     */
    public String getAfterStkCharacter4() {
        return afterStkCharacter4;
    }

    /**
     * 设置调整后特征值4
     *
     * @param afterStkCharacter4 调整后特征值4
     */
    public void setAfterStkCharacter4(String afterStkCharacter4) {
        this.afterStkCharacter4 = afterStkCharacter4 == null ? null : afterStkCharacter4.trim();
    }

    /**
     * 获取调整后特征值5
     *
     * @return after_stk_character5 - 调整后特征值5
     */
    public String getAfterStkCharacter5() {
        return afterStkCharacter5;
    }

    /**
     * 设置调整后特征值5
     *
     * @param afterStkCharacter5 调整后特征值5
     */
    public void setAfterStkCharacter5(String afterStkCharacter5) {
        this.afterStkCharacter5 = afterStkCharacter5 == null ? null : afterStkCharacter5.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }


    /**
     * 获取调整地码编号
     *
     * @return adjust_wb_code - 调整地码编号
     */
    public String getAdjustWbCode() {
        return adjustWbCode;
    }

    /**
     * 设置调整地码编号
     *
     * @param adjustWbCode 调整地码编号
     */
    public void setAdjustWbCode(String adjustWbCode) {
        this.adjustWbCode = adjustWbCode == null ? null : adjustWbCode.trim();
    }


    /**
     * 获取调整SN
     *
     * @return adjust_sn_list - 调整SN
     */
    public String getAdjustSnList() {
        return adjustSnList;
    }

    /**
     * 设置调整SN
     *
     * @param adjustSnList 调整SN
     */
    public void setAdjustSnList(String adjustSnList) {
        this.adjustSnList = adjustSnList == null ? null : adjustSnList.trim();
    }
}
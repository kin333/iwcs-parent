package com.wisdom.iwcs.domain.codec;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_company_ship_date")
public class SCompanyShipDate {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 供下货纸选择的船期ID
     */
    @Column(name = "ship_order_date_id")
    private Integer shipOrderDateId;

    /**
     * 对应船期总表的ID
     */
    @Column(name = "ship_schedule_id")
    private Integer shipScheduleId;

    /**
     * 船东代码
     */
    @Column(name = "shipowner_code")
    private String shipownerCode;

    /**
     * 船代
     */
    @Column(name = "ship_agency")
    private String shipAgency;
    /**
     * 船代id
     */
    @Column(name = "ship_agency_id")
    private String shipAgencyId;

    /**
     * 预计离港日
     */
    @Column(name = "expected_departure_date")
    private String expectedDepartureDate;

    /**
     * 预计起运港
     */
    @Column(name = "expected_departure_port")
    private String expectedDeparturePort;

    /**
     * 船东航次
     */
    @Column(name = "shipowner_voyage")
    private String shipownerVoyage;

    /**
     * 船东呼号
     */
    @Column(name = "shipowner_call_sign")
    private String shipownerCallSign;

    /**
     * 截单日
     */
    @Column(name = "cut_order_time")
    private Date cutOrderTime;

    /**
     * 截港日
     */
    @Column(name = "cut_port_time")
    private Date cutPortTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 录入员
     */
    @Column(name = "data_entry_staff")
    private Integer dataEntryStaff;

    /**
     * 录入日期
     */
    @Column(name = "entry_time")
    private Date entryTime;

    /**
     * VGM截单时间
     */
    @Column(name = "vgm_cut_order_time")
    private Date vgmCutOrderTime;

    /**
     * 1.签单业务已完船
     */
    @Column(name = "sign_oder_complete")
    private String signOderComplete;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 删除标识：1-未删除，0-已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 船代所在的公司id
     */
    @Column(name = "ship_agency_company_id")
    private Integer shipAgencyCompanyId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取供下货纸选择的船期ID
     *
     * @return ship_order_date_id - 供下货纸选择的船期ID
     */
    public Integer getShipOrderDateId() {
        return shipOrderDateId;
    }

    /**
     * 设置供下货纸选择的船期ID
     *
     * @param shipOrderDateId 供下货纸选择的船期ID
     */
    public void setShipOrderDateId(Integer shipOrderDateId) {
        this.shipOrderDateId = shipOrderDateId;
    }

    /**
     * 获取对应船期总表的ID
     *
     * @return ship_schedule_id - 对应船期总表的ID
     */
    public Integer getShipScheduleId() {
        return shipScheduleId;
    }

    /**
     * 设置对应船期总表的ID
     *
     * @param shipScheduleId 对应船期总表的ID
     */
    public void setShipScheduleId(Integer shipScheduleId) {
        this.shipScheduleId = shipScheduleId;
    }

    /**
     * 获取船东代码
     *
     * @return shipowner_code - 船东代码
     */
    public String getShipownerCode() {
        return shipownerCode;
    }

    /**
     * 设置船东代码
     *
     * @param shipownerCode 船东代码
     */
    public void setShipownerCode(String shipownerCode) {
        this.shipownerCode = shipownerCode == null ? null : shipownerCode.trim();
    }

    /**
     * 获取预计离港日
     *
     * @return expected_departure_date - 预计离港日
     */
    public String getExpectedDepartureDate() {
        return expectedDepartureDate;
    }

    /**
     * 设置预计离港日
     *
     * @param expectedDepartureDate 预计离港日
     */
    public void setExpectedDepartureDate(String expectedDepartureDate) {
        this.expectedDepartureDate = expectedDepartureDate == null ? null : expectedDepartureDate.trim();
    }

    /**
     * 获取预计起运港
     *
     * @return expected_departure_port - 预计起运港
     */
    public String getExpectedDeparturePort() {
        return expectedDeparturePort;
    }

    /**
     * 设置预计起运港
     *
     * @param expectedDeparturePort 预计起运港
     */
    public void setExpectedDeparturePort(String expectedDeparturePort) {
        this.expectedDeparturePort = expectedDeparturePort == null ? null : expectedDeparturePort.trim();
    }

    /**
     * 获取船东航次
     *
     * @return shipowner_voyage - 船东航次
     */
    public String getShipownerVoyage() {
        return shipownerVoyage;
    }

    /**
     * 设置船东航次
     *
     * @param shipownerVoyage 船东航次
     */
    public void setShipownerVoyage(String shipownerVoyage) {
        this.shipownerVoyage = shipownerVoyage == null ? null : shipownerVoyage.trim();
    }

    /**
     * 获取船东呼号
     *
     * @return shipowner_call_sign - 船东呼号
     */
    public String getShipownerCallSign() {
        return shipownerCallSign;
    }

    /**
     * 设置船东呼号
     *
     * @param shipownerCallSign 船东呼号
     */
    public void setShipownerCallSign(String shipownerCallSign) {
        this.shipownerCallSign = shipownerCallSign == null ? null : shipownerCallSign.trim();
    }

    /**
     * 获取截单日
     *
     * @return cut_order_time - 截单日
     */
    public Date getCutOrderTime() {
        return cutOrderTime;
    }

    /**
     * 设置截单日
     *
     * @param cutOrderTime 截单日
     */
    public void setCutOrderTime(Date cutOrderTime) {
        this.cutOrderTime = cutOrderTime;
    }

    /**
     * 获取截港日
     *
     * @return cut_port_time - 截港日
     */
    public Date getCutPortTime() {
        return cutPortTime;
    }

    /**
     * 设置截港日
     *
     * @param cutPortTime 截港日
     */
    public void setCutPortTime(Date cutPortTime) {
        this.cutPortTime = cutPortTime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取录入员
     *
     * @return data_entry_staff - 录入员
     */
    public Integer getDataEntryStaff() {
        return dataEntryStaff;
    }

    /**
     * 设置录入员
     *
     * @param dataEntryStaff 录入员
     */
    public void setDataEntryStaff(Integer dataEntryStaff) {
        this.dataEntryStaff = dataEntryStaff;
    }

    /**
     * 获取录入日期
     *
     * @return entry_time - 录入日期
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * 设置录入日期
     *
     * @param entryTime 录入日期
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * 获取VGM截单时间
     *
     * @return vgm_cut_order_time - VGM截单时间
     */
    public Date getVgmCutOrderTime() {
        return vgmCutOrderTime;
    }

    /**
     * 设置VGM截单时间
     *
     * @param vgmCutOrderTime VGM截单时间
     */
    public void setVgmCutOrderTime(Date vgmCutOrderTime) {
        this.vgmCutOrderTime = vgmCutOrderTime;
    }

    /**
     * 获取1.签单业务已完船
     *
     * @return sign_oder_complete - 1.签单业务已完船
     */
    public String getSignOderComplete() {
        return signOderComplete;
    }

    /**
     * 设置1.签单业务已完船
     *
     * @param signOderComplete 1.签单业务已完船
     */
    public void setSignOderComplete(String signOderComplete) {
        this.signOderComplete = signOderComplete == null ? null : signOderComplete.trim();
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
     * 获取更新人
     *
     * @return last_modified_by - 更新人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置更新人
     *
     * @param lastModifiedBy 更新人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
     * 获取删除标识：1-未删除，0-已删除
     *
     * @return delete_flag - 删除标识：1-未删除，0-已删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识：1-未删除，0-已删除
     *
     * @param deleteFlag 删除标识：1-未删除，0-已删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    public String getShipAgency() {
        return shipAgency;
    }

    public void setShipAgency(String shipAgency) {
        this.shipAgency = shipAgency;
    }

    public String getShipAgencyId() {
        return shipAgencyId;
    }

    public void setShipAgencyId(String shipAgencyId) {
        this.shipAgencyId = shipAgencyId;
    }

    public Integer getShipAgencyCompanyId() {
        return shipAgencyCompanyId;
    }

    public void setShipAgencyCompanyId(Integer shipAgencyCompanyId) {
        this.shipAgencyCompanyId = shipAgencyCompanyId;
    }
}
package com.wisdom.iwcs.domain.instock.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "instock_order")
public class InstockOrderDTO {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 单据类型
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 仓库编码
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 库区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 任务单来源，0本地创建，1上游同步
     */
    private String src;

    /**
     * 预留字段
     */
    @Column(name = "order_prop1")
    private String orderProp1;

    /**
     * 预留字段
     */
    @Column(name = "order_prop2")
    private String orderProp2;

    /**
     * 预留字段
     */
    @Column(name = "order_prop3")
    private String orderProp3;

    /**
     * 预留字段
     */
    @Column(name = "order_prop4")
    private String orderProp4;

    /**
     * 预留字段
     */
    @Column(name = "order_prop5")
    private String orderProp5;

    /**
     * 备注
     */
    private String remark;

    /**
     * 有效标记，0有效，1无效
     */
    @Column(name = "valid_flag")
    private Integer validFlag;

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
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    private List<InstockOrderDetailDTO> instockOrderDetailDTOList;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取单据类型
     *
     * @return order_type - 单据类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置单据类型
     *
     * @param orderType 单据类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 获取仓库编码
     *
     * @return wh_code - 仓库编码
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库编码
     *
     * @param whCode 仓库编码
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取库区代码
     *
     * @return area_code - 库区代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区代码
     *
     * @param areaCode 库区代码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取任务单来源，0本地创建，1上游同步
     *
     * @return src - 任务单来源，0本地创建，1上游同步
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置任务单来源，0本地创建，1上游同步
     *
     * @param src 任务单来源，0本地创建，1上游同步
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * 获取预留字段
     *
     * @return order_prop1 - 预留字段
     */
    public String getOrderProp1() {
        return orderProp1;
    }

    /**
     * 设置预留字段
     *
     * @param orderProp1 预留字段
     */
    public void setOrderProp1(String orderProp1) {
        this.orderProp1 = orderProp1 == null ? null : orderProp1.trim();
    }

    /**
     * 获取预留字段
     *
     * @return order_prop2 - 预留字段
     */
    public String getOrderProp2() {
        return orderProp2;
    }

    /**
     * 设置预留字段
     *
     * @param orderProp2 预留字段
     */
    public void setOrderProp2(String orderProp2) {
        this.orderProp2 = orderProp2 == null ? null : orderProp2.trim();
    }

    /**
     * 获取预留字段
     *
     * @return order_prop3 - 预留字段
     */
    public String getOrderProp3() {
        return orderProp3;
    }

    /**
     * 设置预留字段
     *
     * @param orderProp3 预留字段
     */
    public void setOrderProp3(String orderProp3) {
        this.orderProp3 = orderProp3 == null ? null : orderProp3.trim();
    }

    /**
     * 获取预留字段
     *
     * @return order_prop4 - 预留字段
     */
    public String getOrderProp4() {
        return orderProp4;
    }

    /**
     * 设置预留字段
     *
     * @param orderProp4 预留字段
     */
    public void setOrderProp4(String orderProp4) {
        this.orderProp4 = orderProp4 == null ? null : orderProp4.trim();
    }

    /**
     * 获取预留字段
     *
     * @return order_prop5 - 预留字段
     */
    public String getOrderProp5() {
        return orderProp5;
    }

    /**
     * 设置预留字段
     *
     * @param orderProp5 预留字段
     */
    public void setOrderProp5(String orderProp5) {
        this.orderProp5 = orderProp5 == null ? null : orderProp5.trim();
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
     * 获取有效标记，0有效，1无效
     *
     * @return valid_flag - 有效标记，0有效，1无效
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * 设置有效标记，0有效，1无效
     *
     * @param validFlag 有效标记，0有效，1无效
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
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
     * 获取最后修改人
     *
     * @return last_modified_by - 最后修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifiedBy 最后修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_modified_time - 最后修改时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastModifiedTime 最后修改时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public List<InstockOrderDetailDTO> getInstockOrderDetailDTOList() {
        return instockOrderDetailDTOList;
    }

    public void setInstockOrderDetailDTOList(List<InstockOrderDetailDTO> instockOrderDetailDTOList) {
        this.instockOrderDetailDTOList = instockOrderDetailDTOList;
    }
}
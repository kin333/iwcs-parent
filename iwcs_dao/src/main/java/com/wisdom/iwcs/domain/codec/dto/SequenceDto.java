package com.wisdom.iwcs.domain.codec.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "codec_sequence")
public class SequenceDto {
    /**
     * id
     */
    @Id
    private Integer id;

    /**

     */
    @Column(name = "seq_name")
    private String seqName;

    /**

     */
    @Column(name = "min_value")
    private Integer minValue;

    /**

     */
    @Column(name = "max_value")
    private Integer maxValue;

    /**

     */
    @Column(name = "current_val")
    private Integer currentVal;

    /**

     */
    @Column(name = "increment_val")
    private Integer incrementVal;

    /**

     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**

     */
    @Column(name = "created_time")
    private Date createdTime;

    /**

     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**

     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**

     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**

     */
    private String remark;

    /**
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *

     */
    public String getSeqName() {
        return seqName;
    }

    /**
     *

     */
    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    /**
     *

     */
    public Integer getMinValue() {
        return minValue;
    }

    /**
     *

     */
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    /**
     *

     */
    public Integer getMaxValue() {
        return maxValue;
    }

    /**
     *

     */
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    /**
     *

     */
    public Integer getCurrentVal() {
        return currentVal;
    }

    /**
     *

     */
    public void setCurrentVal(Integer currentVal) {
        this.currentVal = currentVal;
    }

    /**
     *

     */
    public Integer getIncrementVal() {
        return incrementVal;
    }

    /**
     *

     */
    public void setIncrementVal(Integer incrementVal) {
        this.incrementVal = incrementVal;
    }

    /**
     *

     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     *

     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *

     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     *

     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *

     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     *

     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     *

     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     *

     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     *

     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     *

     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     *

     */
    public String getRemark() {
        return remark;
    }

    /**
     *

     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
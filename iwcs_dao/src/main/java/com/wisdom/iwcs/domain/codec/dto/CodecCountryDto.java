package com.wisdom.iwcs.domain.codec.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "codec_country")
public class CodecCountryDto {
    @Id
    private Integer id;

    /**
     *
     */
    @Column(name = "country_ch")
    private String countryCh;

    /**
     *
     */
    @Column(name = "country_en")
    private String countryEn;

    /**
     *
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     *
     */
    private String identifier;

    /**
     *
     */
    @Column(name = "freeze_flag")
    private String freezeFlag;

    /**
     *
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     *
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     *
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     *
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     *
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    @Column(name = "created_by_name")
    private String createdByName;

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

     */
    public String getCountryCh() {
        return countryCh;
    }

    /**

     */
    public void setCountryCh(String countryCh) {
        this.countryCh = countryCh == null ? null : countryCh.trim();
    }

    /**

     */
    public String getCountryEn() {
        return countryEn;
    }

    /**

     */
    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn == null ? null : countryEn.trim();
    }

    /**

     */
    public String getCountryCode() {
        return countryCode;
    }

    /**

     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    /**
     * @return identifier -
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    /**
     * @return freeze_flag -
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * @param freezeFlag
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag == null ? null : freezeFlag.trim();
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_time -
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return last_modified_time -
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * @param lastModifiedTime
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * @return delete_flag -
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}
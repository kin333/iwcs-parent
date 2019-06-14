package com.wisdom.iwcs.domain.codec;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "codec_business_code")
public class BusinessCode {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 代码分类 VESCD=船名，PORTCD=港口，CTNCLCD=尺寸箱型，PKTP=包装，CTNOPE=船公司，FORWARD=代理，STATUS=箱动态，AGENCY=船代
     */
    @Column(name = "code_type")
    private String codeType;

    /**
     * 代码，港口代码以UN代码为准，尺寸箱型为实际类型，如20GP，其他代码自定义
     */
    private String code;

    /**
     * 代码中文名称
     */
    @Column(name = "chinese_name")
    private String chineseName;

    /**
     * 代码英文名称
     */
    @Column(name = "english_name")
    private String englishName;

    /**
     * 州、地区代码（港口代码使用）
     */
    @Column(name = "state_code")
    private String stateCode;

    /**
     * 州名、地区名（港口代码使用）
     */
    @Column(name = "state_name")
    private String stateName;

    /**
     * 国家代码（港口代码使用）
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
     * 国家名称（港口代码使用）
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 排序
     */
    @Column(name = "sort_key")
    private Integer sortKey;

    /**
     * 扩展字段 类型为船名时，表示船舶呼号 ;港口时，为航线代码
     */
    @Column(name = "item_one")
    private String itemOne;

    @Column(name = "item_two")
    private String itemTwo;

    @Column(name = "item_three")
    private String itemThree;

    /**
     * 冻结标志
     */
    @Column(name = "freeze_flag")
    private String freezeFlag;

    /**
     * 备注
     */
    private String remark;

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
     * 获取代码分类 VESCD=船名，PORTCD=港口，CTNCLCD=尺寸箱型，PKTP=包装，CTNOPE=船公司，FORWARD=代理，STATUS=箱动态，AGENCY=船代
     *
     * @return code_type - 代码分类 VESCD=船名，PORTCD=港口，CTNCLCD=尺寸箱型，PKTP=包装，CTNOPE=船公司，FORWARD=代理，STATUS=箱动态，AGENCY=船代
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * 设置代码分类 VESCD=船名，PORTCD=港口，CTNCLCD=尺寸箱型，PKTP=包装，CTNOPE=船公司，FORWARD=代理，STATUS=箱动态，AGENCY=船代
     *
     * @param codeType 代码分类 VESCD=船名，PORTCD=港口，CTNCLCD=尺寸箱型，PKTP=包装，CTNOPE=船公司，FORWARD=代理，STATUS=箱动态，AGENCY=船代
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    /**
     * 获取代码，港口代码以UN代码为准，尺寸箱型为实际类型，如20GP，其他代码自定义
     *
     * @return code - 代码，港口代码以UN代码为准，尺寸箱型为实际类型，如20GP，其他代码自定义
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码，港口代码以UN代码为准，尺寸箱型为实际类型，如20GP，其他代码自定义
     *
     * @param code 代码，港口代码以UN代码为准，尺寸箱型为实际类型，如20GP，其他代码自定义
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取代码中文名称
     *
     * @return chinese_name - 代码中文名称
     */
    public String getChineseName() {
        return chineseName;
    }

    /**
     * 设置代码中文名称
     *
     * @param chineseName 代码中文名称
     */
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    /**
     * 获取代码英文名称
     *
     * @return english_name - 代码英文名称
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置代码英文名称
     *
     * @param englishName 代码英文名称
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    /**
     * 获取州、地区代码（港口代码使用）
     *
     * @return state_code - 州、地区代码（港口代码使用）
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * 设置州、地区代码（港口代码使用）
     *
     * @param stateCode 州、地区代码（港口代码使用）
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
    }

    /**
     * 获取州名、地区名（港口代码使用）
     *
     * @return state_name - 州名、地区名（港口代码使用）
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * 设置州名、地区名（港口代码使用）
     *
     * @param stateName 州名、地区名（港口代码使用）
     */
    public void setStateName(String stateName) {
        this.stateName = stateName == null ? null : stateName.trim();
    }

    /**
     * 获取国家代码（港口代码使用）
     *
     * @return country_code - 国家代码（港口代码使用）
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置国家代码（港口代码使用）
     *
     * @param countryCode 国家代码（港口代码使用）
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    /**
     * 获取国家名称（港口代码使用）
     *
     * @return country_name - 国家名称（港口代码使用）
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家名称（港口代码使用）
     *
     * @param countryName 国家名称（港口代码使用）
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     * 获取排序
     *
     * @return sort_key - 排序
     */
    public Integer getSortKey() {
        return sortKey;
    }

    /**
     * 设置排序
     *
     * @param sortKey 排序
     */
    public void setSortKey(Integer sortKey) {
        this.sortKey = sortKey;
    }


    public String getItemOne() {
        return itemOne;
    }

    public void setItemOne(String itemOne) {
        this.itemOne = itemOne;
    }

    public String getItemTwo() {
        return itemTwo;
    }

    public void setItemTwo(String itemTwo) {
        this.itemTwo = itemTwo;
    }

    public String getItemThree() {
        return itemThree;
    }

    public void setItemThree(String itemThree) {
        this.itemThree = itemThree;
    }

    /**
     * 获取冻结标志
     *
     * @return freeze_flag - 冻结标志
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * 设置冻结标志
     *
     * @param freezeFlag 冻结标志
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag == null ? null : freezeFlag.trim();
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
}
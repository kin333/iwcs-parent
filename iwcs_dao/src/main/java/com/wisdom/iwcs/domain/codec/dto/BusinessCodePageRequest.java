package com.wisdom.iwcs.domain.codec.dto;

import com.wisdom.iwcs.common.utils.GridPageRequest;

/**
 * @author Devin
 * @date 2018-03-14.
 */
public class BusinessCodePageRequest extends GridPageRequest {

    private Integer businessCodeId;

    private String code;

    private String codeType;

    private String airPortFlag;

    private String freezeFlag;

    private String deleteFlag;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 从首字母开始查找  1为是，其他为否
     **/
    private String searchFromBegin;


    private String ignoreFreeze;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getSearchFromBegin() {
        return searchFromBegin;
    }

    public void setSearchFromBegin(String searchFromBegin) {
        this.searchFromBegin = searchFromBegin;
    }

    public Integer getBusinessCodeId() {
        return businessCodeId;
    }

    public void setBusinessCodeId(Integer businessCodeId) {
        this.businessCodeId = businessCodeId;
    }

    public String getAirPortFlag() {
        return airPortFlag;
    }

    public void setAirPortFlag(String airPortFlag) {
        this.airPortFlag = airPortFlag;
    }

    public String getFreezeFlag() {
        return freezeFlag;
    }

    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getIgnoreFreeze() {
        return ignoreFreeze;
    }

    public void setIgnoreFreeze(String ignoreFreeze) {
        this.ignoreFreeze = ignoreFreeze;
    }
}

package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.Dictionary;

import java.io.Serializable;

public class DictionaryDto extends Dictionary implements Serializable {

    private String statusStr;//激活状态
    private String createdByStr;//创建人
    private String lastModifiedByStr;//更新人
    private String createdTimeStr;//创建时间
    private String lastModifiedTimeStr;//更新时间

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getCreatedByStr() {
        return createdByStr;
    }

    public void setCreatedByStr(String createdByStr) {
        this.createdByStr = createdByStr;
    }

    public String getLastModifiedByStr() {
        return lastModifiedByStr;
    }

    public void setLastModifiedByStr(String lastModifiedByStr) {
        this.lastModifiedByStr = lastModifiedByStr;
    }

    public String getCreatedTimeStr() {
        return createdTimeStr;
    }

    public void setCreatedTimeStr(String createdTimeStr) {
        this.createdTimeStr = createdTimeStr;
    }

    public String getLastModifiedTimeStr() {
        return lastModifiedTimeStr;
    }

    public void setLastModifiedTimeStr(String lastModifiedTimeStr) {
        this.lastModifiedTimeStr = lastModifiedTimeStr;
    }
}
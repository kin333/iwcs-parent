package com.wisdom.iwcs.domain.task;


import javax.persistence.Id;
import java.util.List;

/**
 * 用于任务模板配置
 */
public class TaskRelSubMain {

    private Integer id;
    private String mainTaskTypeCode;
    private String subTaskTypeCode;
    private String templCode;
    private String subTaskTypName;
    private Integer subTaskSeq;
    private Boolean deleteFlag;

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainTaskTypeCode() {
        return mainTaskTypeCode;
    }

    public void setMainTaskTypeCode(String mainTaskTypeCode) {
        this.mainTaskTypeCode = mainTaskTypeCode;
    }

    public String getSubTaskTypeCode() {
        return subTaskTypeCode;
    }

    public void setSubTaskTypeCode(String subTaskTypeCode) {
        this.subTaskTypeCode = subTaskTypeCode;
    }

    public String getTemplCode() {
        return templCode;
    }

    public void setTemplCode(String templCode) {
        this.templCode = templCode;
    }

    public String getSubTaskTypName() {
        return subTaskTypName;
    }

    public void setSubTaskTypName(String subTaskTypName) {
        this.subTaskTypName = subTaskTypName;
    }

    public Integer getSubTaskSeq() {
        return subTaskSeq;
    }

    public void setSubTaskSeq(Integer subTaskSeq) {
        this.subTaskSeq = subTaskSeq;
    }
}
package com.wisdom.iwcs.domain.hikSync;

/**
 * @author cecilia.yang
 */
public class NotifyClientDataDTO {
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 货位编号
     */
    private String binCode;

    @Override
    public String toString() {
        return "NotifyClientDataDTO{" +
                "taskCode='" + taskCode + '\'' +
                ", binCode='" + binCode + '\'' +
                '}';
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }
}

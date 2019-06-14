package com.wisdom.iwcs.domain.hikSync;

public class OutPodDataDTO {

    /**
     * 任务单号，必须为32位UUID
     */
    private String taskCode;
    /**
     * 顺序出库任务，设置任务组
     */
    private String groupId;

    /**
     * 顺序出库任务，设置出库顺序
     */
    private String sequence;


    /**
     * 货位编号
     */
    private String binCode;

    /**
     * 任务优先级
     */
    private String priority;

    /**
     * 工作台编号
     */
    private String wbCode;

    /**
     * 默认空或者举升
     * 到达目的地后是否举升着
     * 举升-0
     * 放下-1
     * 放下释放小车-2
     */
    private String liftStatus;

    /**
     * 提前拣货标识
     * 1-表示未到工作台执行回库任务
     */
    private String prePick;

    public String getPrePick() {
        return prePick;
    }

    public void setPrePick(String prePick) {
        this.prePick = prePick;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getLiftStatus() {
        return liftStatus;
    }

    public void setLiftStatus(String liftStatus) {
        this.liftStatus = liftStatus;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}

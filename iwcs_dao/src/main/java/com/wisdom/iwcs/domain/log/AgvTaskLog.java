package com.wisdom.iwcs.domain.log;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "agv_task_log")
public class AgvTaskLog {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 类型，1到达工作台，2到达排队区，待拓展
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 任务编号，和海康交互用，对应本次任务的task_code
     */
    @Column(name = "task_seq")
    private String taskSeq;

    /**
     * 仓位编号，该货架的任意一个仓位编号，冗余用
     */
    @Column(name = "bin_code")
    private String binCode;

    /**
     * Agv子任务编号（厂家内部子任务）
     */
    @Column(name = "agv_sub_task_seq")
    private String agvSubTaskSeq;

    /**
     * 起始地码
     */
    @Column(name = "src_ber_code")
    private String srcBerCode;

    /**
     * 目标地码
     */
    @Column(name = "trgt_ber_code")
    private String trgtBerCode;

    /**
     * 当前地码
     */
    @Column(name = "cur_bercode")
    private String curBercode;

    /**
     * AGV车号
     */
    @Column(name = "agv_code")
    private String agvCode;

    /**
     * 发生时间
     */
    @Column(name = "task_time")
    private String taskTime;

    /**
     * agv预留
     */
    @Column(name = "agv_prop1")
    private String agvProp1;

    /**
     * agv预留
     */
    @Column(name = "agv_prop2")
    private String agvProp2;

    /**
     * agv预留
     */
    @Column(name = "agv_prop3")
    private String agvProp3;

    /**
     * 日志来源类型：AGV,iwcs,
     */
    @Column(name = "source_type")
    private String sourceType;

    /**
     * 日志来源标示代码
     */
    @Column(name = "source_code")
    private String sourceCode;

    /**
     * 日志来源预留参数
     */
    @Column(name = "source_prop1")
    private String sourceProp1;

    /**
     * 日志来源预留参数
     */
    @Column(name = "source_prop2")
    private String sourceProp2;

    /**
     * 日志来源预留参数
     */
    @Column(name = "source_prop3")
    private String sourceProp3;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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

    /**
     * 获取类型，1到达工作台，2到达排队区，待拓展
     *
     * @return log_type - 类型，1到达工作台，2到达排队区，待拓展
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 设置类型，1到达工作台，2到达排队区，待拓展
     *
     * @param logType 类型，1到达工作台，2到达排队区，待拓展
     */
    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    /**
     * 获取货架编号
     *
     * @return pod_code - 货架编号
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架编号
     *
     * @param podCode 货架编号
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
    }

    /**
     * 获取任务编号，和海康交互用，对应本次任务的task_code
     *
     * @return task_seq - 任务编号，和海康交互用，对应本次任务的task_code
     */
    public String getTaskSeq() {
        return taskSeq;
    }

    /**
     * 设置任务编号，和海康交互用，对应本次任务的task_code
     *
     * @param taskSeq 任务编号，和海康交互用，对应本次任务的task_code
     */
    public void setTaskSeq(String taskSeq) {
        this.taskSeq = taskSeq == null ? null : taskSeq.trim();
    }

    /**
     * 获取仓位编号，该货架的任意一个仓位编号，冗余用
     *
     * @return bin_code - 仓位编号，该货架的任意一个仓位编号，冗余用
     */
    public String getBinCode() {
        return binCode;
    }

    /**
     * 设置仓位编号，该货架的任意一个仓位编号，冗余用
     *
     * @param binCode 仓位编号，该货架的任意一个仓位编号，冗余用
     */
    public void setBinCode(String binCode) {
        this.binCode = binCode == null ? null : binCode.trim();
    }

    /**
     * 获取Agv子任务编号（厂家内部子任务）
     *
     * @return agv_sub_task_seq - Agv子任务编号（厂家内部子任务）
     */
    public String getAgvSubTaskSeq() {
        return agvSubTaskSeq;
    }

    /**
     * 设置Agv子任务编号（厂家内部子任务）
     *
     * @param agvSubTaskSeq Agv子任务编号（厂家内部子任务）
     */
    public void setAgvSubTaskSeq(String agvSubTaskSeq) {
        this.agvSubTaskSeq = agvSubTaskSeq == null ? null : agvSubTaskSeq.trim();
    }

    /**
     * 获取起始地码
     *
     * @return src_ber_code - 起始地码
     */
    public String getSrcBerCode() {
        return srcBerCode;
    }

    /**
     * 设置起始地码
     *
     * @param srcBerCode 起始地码
     */
    public void setSrcBerCode(String srcBerCode) {
        this.srcBerCode = srcBerCode == null ? null : srcBerCode.trim();
    }

    /**
     * 获取目标地码
     *
     * @return trgt_ber_code - 目标地码
     */
    public String getTrgtBerCode() {
        return trgtBerCode;
    }

    /**
     * 设置目标地码
     *
     * @param trgtBerCode 目标地码
     */
    public void setTrgtBerCode(String trgtBerCode) {
        this.trgtBerCode = trgtBerCode == null ? null : trgtBerCode.trim();
    }

    /**
     * 获取当前地码
     *
     * @return cur_bercode - 当前地码
     */
    public String getCurBercode() {
        return curBercode;
    }

    /**
     * 设置当前地码
     *
     * @param curBercode 当前地码
     */
    public void setCurBercode(String curBercode) {
        this.curBercode = curBercode == null ? null : curBercode.trim();
    }

    /**
     * 获取AGV车号
     *
     * @return agv_code - AGV车号
     */
    public String getAgvCode() {
        return agvCode;
    }

    /**
     * 设置AGV车号
     *
     * @param agvCode AGV车号
     */
    public void setAgvCode(String agvCode) {
        this.agvCode = agvCode == null ? null : agvCode.trim();
    }

    /**
     * 获取发生时间
     *
     * @return task_time - 发生时间
     */
    public String getTaskTime() {
        return taskTime;
    }

    /**
     * 设置发生时间
     *
     * @param taskTime 发生时间
     */
    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime == null ? null : taskTime.trim();
    }

    /**
     * 获取agv预留
     *
     * @return agv_prop1 - agv预留
     */
    public String getAgvProp1() {
        return agvProp1;
    }

    /**
     * 设置agv预留
     *
     * @param agvProp1 agv预留
     */
    public void setAgvProp1(String agvProp1) {
        this.agvProp1 = agvProp1 == null ? null : agvProp1.trim();
    }

    /**
     * 获取agv预留
     *
     * @return agv_prop2 - agv预留
     */
    public String getAgvProp2() {
        return agvProp2;
    }

    /**
     * 设置agv预留
     *
     * @param agvProp2 agv预留
     */
    public void setAgvProp2(String agvProp2) {
        this.agvProp2 = agvProp2 == null ? null : agvProp2.trim();
    }

    /**
     * 获取agv预留
     *
     * @return agv_prop3 - agv预留
     */
    public String getAgvProp3() {
        return agvProp3;
    }

    /**
     * 设置agv预留
     *
     * @param agvProp3 agv预留
     */
    public void setAgvProp3(String agvProp3) {
        this.agvProp3 = agvProp3 == null ? null : agvProp3.trim();
    }

    /**
     * 获取日志来源类型：AGV,iwcs,
     *
     * @return source_type - 日志来源类型：AGV,iwcs,
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置日志来源类型：AGV,iwcs,
     *
     * @param sourceType 日志来源类型：AGV,iwcs,
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    /**
     * 获取日志来源标示代码
     *
     * @return source_code - 日志来源标示代码
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * 设置日志来源标示代码
     *
     * @param sourceCode 日志来源标示代码
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }

    /**
     * 获取日志来源预留参数
     *
     * @return source_prop1 - 日志来源预留参数
     */
    public String getSourceProp1() {
        return sourceProp1;
    }

    /**
     * 设置日志来源预留参数
     *
     * @param sourceProp1 日志来源预留参数
     */
    public void setSourceProp1(String sourceProp1) {
        this.sourceProp1 = sourceProp1 == null ? null : sourceProp1.trim();
    }

    /**
     * 获取日志来源预留参数
     *
     * @return source_prop2 - 日志来源预留参数
     */
    public String getSourceProp2() {
        return sourceProp2;
    }

    /**
     * 设置日志来源预留参数
     *
     * @param sourceProp2 日志来源预留参数
     */
    public void setSourceProp2(String sourceProp2) {
        this.sourceProp2 = sourceProp2 == null ? null : sourceProp2.trim();
    }

    /**
     * 获取日志来源预留参数
     *
     * @return source_prop3 - 日志来源预留参数
     */
    public String getSourceProp3() {
        return sourceProp3;
    }

    /**
     * 设置日志来源预留参数
     *
     * @param sourceProp3 日志来源预留参数
     */
    public void setSourceProp3(String sourceProp3) {
        this.sourceProp3 = sourceProp3 == null ? null : sourceProp3.trim();
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
}
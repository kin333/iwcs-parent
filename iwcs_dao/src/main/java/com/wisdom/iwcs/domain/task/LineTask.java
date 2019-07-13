package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "line_task")
public class LineTask {
    @Id
    private Integer id;

    /**
     * 请求码
     */
    @Column(name = "line_req_code")
    private String lineReqCode;

    /**
     * 下货坐标点
     */
    @Column(name = "web_code")
    private String webCode;

    /**
     * 线体状态
     */
    @Column(name = "line_status")
    private String lineStatus;

    /**
     * 作业方式
     */
    @Column(name = "work_type")
    private String workType;

    /**
     * 命令类型码
     */
    @Column(name = "cmd_type")
    private String cmdType;

    /**
     * 地址
     */
    @Column(name = "line_url")
    private String lineUrl;

    /**
     * 状态更新时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 备注
     */
    private String remark;

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
     * 获取请求码
     *
     * @return line_req_code - 请求码
     */
    public String getLineReqCode() {
        return lineReqCode;
    }

    /**
     * 设置请求码
     *
     * @param lineReqCode 请求码
     */
    public void setLineReqCode(String lineReqCode) {
        this.lineReqCode = lineReqCode == null ? null : lineReqCode.trim();
    }

    /**
     * 获取下货坐标点
     *
     * @return web_code - 下货坐标点
     */
    public String getWebCode() {
        return webCode;
    }

    /**
     * 设置下货坐标点
     *
     * @param webCode 下货坐标点
     */
    public void setWebCode(String webCode) {
        this.webCode = webCode == null ? null : webCode.trim();
    }

    /**
     * 获取线体状态
     *
     * @return line_status - 线体状态
     */
    public String getLineStatus() {
        return lineStatus;
    }

    /**
     * 设置线体状态
     *
     * @param lineStatus 线体状态
     */
    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus == null ? null : lineStatus.trim();
    }

    /**
     * 获取作业方式
     *
     * @return work_type - 作业方式
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * 设置作业方式
     *
     * @param workType 作业方式
     */
    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    /**
     * 获取命令类型码
     *
     * @return cmd_type - 命令类型码
     */
    public String getCmdType() {
        return cmdType;
    }

    /**
     * 设置命令类型码
     *
     * @param cmdType 命令类型码
     */
    public void setCmdType(String cmdType) {
        this.cmdType = cmdType == null ? null : cmdType.trim();
    }

    /**
     * 获取地址
     *
     * @return line_url - 地址
     */
    public String getLineUrl() {
        return lineUrl;
    }

    /**
     * 设置地址
     *
     * @param lineUrl 地址
     */
    public void setLineUrl(String lineUrl) {
        this.lineUrl = lineUrl == null ? null : lineUrl.trim();
    }

    /**
     * 获取状态更新时间
     *
     * @return created_time - 状态更新时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置状态更新时间
     *
     * @param createdTime 状态更新时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
}
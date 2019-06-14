package com.wisdom.iwcs.domain.log;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "interface_log")
public class InterfaceLog {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 接收方法时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 调用接口方，如RRS,CML等
     */
    @Column(name = "src_client_code")
    private String srcClientCode;

    /**
     * 接口方法code
     */
    @Column(name = "interface_code")
    private String interfaceCode;

    /**
     * 接口方法描述
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 接口方法结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 接口耗费时间
     */
    @Column(name = "time_cost")
    private String timeCost;

    /**
     * 线程id
     */
    @Column(name = "sev_thread_id")
    private String sevThreadId;

    /**
     * 线程名称
     */
    @Column(name = "sev_thread_name")
    private String sevThreadName;

    /**
     * 请求终端类型
     */
    @Column(name = "req_client_type")
    private String reqClientType;

    @Column(name = "req_ip")
    private String reqIp;

    /**
     * 请求唯一编码
     */
    @Column(name = "req_code")
    private String reqCode;

    /**
     * 请求预留参数
     */
    @Column(name = "req_prop1")
    private String reqProp1;

    /**
     * 请求预留参数
     */
    @Column(name = "req_prop2")
    private String reqProp2;

    /**
     * 请求预留参数
     */
    @Column(name = "req_prop3")
    private String reqProp3;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 接口传入参数
     */
    @Column(name = "in_param")
    private String inParam;

    /**
     * 接口返回参数
     */
    @Column(name = "out_param")
    private String outParam;

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
     * 获取接收方法时间
     *
     * @return receive_time - 接收方法时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置接收方法时间
     *
     * @param receiveTime 接收方法时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取调用接口方，如RRS,CML等
     *
     * @return src_client_code - 调用接口方，如RRS,CML等
     */
    public String getSrcClientCode() {
        return srcClientCode;
    }

    /**
     * 设置调用接口方，如RRS,CML等
     *
     * @param srcClientCode 调用接口方，如RRS,CML等
     */
    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode == null ? null : srcClientCode.trim();
    }

    /**
     * 获取接口方法code
     *
     * @return interface_code - 接口方法code
     */
    public String getInterfaceCode() {
        return interfaceCode;
    }

    /**
     * 设置接口方法code
     *
     * @param interfaceCode 接口方法code
     */
    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode == null ? null : interfaceCode.trim();
    }

    /**
     * 获取接口方法描述
     *
     * @return interface_name - 接口方法描述
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * 设置接口方法描述
     *
     * @param interfaceName 接口方法描述
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    /**
     * 获取接口方法结束时间
     *
     * @return end_time - 接口方法结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置接口方法结束时间
     *
     * @param endTime 接口方法结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取接口耗费时间
     *
     * @return time_cost - 接口耗费时间
     */
    public String getTimeCost() {
        return timeCost;
    }

    /**
     * 设置接口耗费时间
     *
     * @param timeCost 接口耗费时间
     */
    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost == null ? null : timeCost.trim();
    }

    /**
     * 获取线程id
     *
     * @return sev_thread_id - 线程id
     */
    public String getSevThreadId() {
        return sevThreadId;
    }

    /**
     * 设置线程id
     *
     * @param sevThreadId 线程id
     */
    public void setSevThreadId(String sevThreadId) {
        this.sevThreadId = sevThreadId == null ? null : sevThreadId.trim();
    }

    /**
     * 获取线程名称
     *
     * @return sev_thread_name - 线程名称
     */
    public String getSevThreadName() {
        return sevThreadName;
    }

    /**
     * 设置线程名称
     *
     * @param sevThreadName 线程名称
     */
    public void setSevThreadName(String sevThreadName) {
        this.sevThreadName = sevThreadName == null ? null : sevThreadName.trim();
    }

    /**
     * 获取请求终端类型
     *
     * @return req_client_type - 请求终端类型
     */
    public String getReqClientType() {
        return reqClientType;
    }

    /**
     * 设置请求终端类型
     *
     * @param reqClientType 请求终端类型
     */
    public void setReqClientType(String reqClientType) {
        this.reqClientType = reqClientType == null ? null : reqClientType.trim();
    }

    /**
     * @return req_ip
     */
    public String getReqIp() {
        return reqIp;
    }

    /**
     * @param reqIp
     */
    public void setReqIp(String reqIp) {
        this.reqIp = reqIp == null ? null : reqIp.trim();
    }

    /**
     * 获取请求唯一编码
     *
     * @return req_code - 请求唯一编码
     */
    public String getReqCode() {
        return reqCode;
    }

    /**
     * 设置请求唯一编码
     *
     * @param reqCode 请求唯一编码
     */
    public void setReqCode(String reqCode) {
        this.reqCode = reqCode == null ? null : reqCode.trim();
    }

    /**
     * 获取请求预留参数
     *
     * @return req_prop1 - 请求预留参数
     */
    public String getReqProp1() {
        return reqProp1;
    }

    /**
     * 设置请求预留参数
     *
     * @param reqProp1 请求预留参数
     */
    public void setReqProp1(String reqProp1) {
        this.reqProp1 = reqProp1 == null ? null : reqProp1.trim();
    }

    /**
     * 获取请求预留参数
     *
     * @return req_prop2 - 请求预留参数
     */
    public String getReqProp2() {
        return reqProp2;
    }

    /**
     * 设置请求预留参数
     *
     * @param reqProp2 请求预留参数
     */
    public void setReqProp2(String reqProp2) {
        this.reqProp2 = reqProp2 == null ? null : reqProp2.trim();
    }

    /**
     * 获取请求预留参数
     *
     * @return req_prop3 - 请求预留参数
     */
    public String getReqProp3() {
        return reqProp3;
    }

    /**
     * 设置请求预留参数
     *
     * @param reqProp3 请求预留参数
     */
    public void setReqProp3(String reqProp3) {
        this.reqProp3 = reqProp3 == null ? null : reqProp3.trim();
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
     * 获取接口传入参数
     *
     * @return in_param - 接口传入参数
     */
    public String getInParam() {
        return inParam;
    }

    /**
     * 设置接口传入参数
     *
     * @param inParam 接口传入参数
     */
    public void setInParam(String inParam) {
        this.inParam = inParam == null ? null : inParam.trim();
    }

    /**
     * 获取接口返回参数
     *
     * @return out_param - 接口返回参数
     */
    public String getOutParam() {
        return outParam;
    }

    /**
     * 设置接口返回参数
     *
     * @param outParam 接口返回参数
     */
    public void setOutParam(String outParam) {
        this.outParam = outParam == null ? null : outParam.trim();
    }
}